package GoldGame;
import java.util.*;

public class ItemManager
{
	public static ItemManager instance;
	public ArrayList<Item> activatedItemList = new ArrayList<Item>();
	
	private final int itemCount;
	private Queue<Item>[] itemPoolQueue;
	private int activatedItemId = -1;
	
	private float[] itemProbabilities;
	
	private float remainItemTime;
	
	public float fastItemTime = -1f;
	public float slowItemTime = -1f;
	
	public SpeedArrow speedArrow;
	
	
	ItemManager()
	{
		instance = this;
		itemCount = 7;		//아이템 총 세개임
		
		itemProbabilities = new float[itemCount];
		
		itemProbabilities[0] = 50;		//코인  (+1점)
		itemProbabilities[1] = 10;		//골드바 (+5점)
		itemProbabilities[2] = 30f;		//도둑햄구(-5점)
		itemProbabilities[3] = 0.5f;	//황금햄구(모든 도둑햄구가 골드바로 바뀜)
		itemProbabilities[4] = 0.5f;	//시간 증가
		itemProbabilities[5] = 3f;
		itemProbabilities[6] = 3f;

		
		remainItemTime = 0;
		
		itemPoolQueue = new Queue[itemCount];
		
		
		for(int i = 0 ; i < itemCount ; i++)
		{
			itemPoolQueue[i] = new LinkedList<Item>();
		}
		
		for(int i = 0 ; i < 100 ; i++)		//아이템을 미리 많이 만들어서 큐에 담아놓는다. 필요시 빼고 아이템이 사라져야할때 다시 넣음(오브젝트 풀링)
		{
			itemPoolQueue[0].add(new CoinItem());
			itemPoolQueue[1].add(new GoldBarItem());
			itemPoolQueue[2].add(new MinusItem());
			itemPoolQueue[3].add(new GoldTheifItem());
			itemPoolQueue[4].add(new TimePlusItem());
			itemPoolQueue[5].add(new FastItem());
			itemPoolQueue[6].add(new SlowItem());

		}
		
		speedArrow = new SpeedArrow();
	}
	
	public void TrySetItem()
	{
		remainItemTime -= GameManager.deltaTime;
		
		if(remainItemTime <= 0f)
		{
			remainItemTime = 0.09f;			//아이템이 다시 떨어지기까지 걸리는 시간
			int choicedItemNumber = GetChoicedItemNumber();
			
			Item item = GetItemFromObjectPool(choicedItemNumber);
			
			float randomPosX = (float)Math.random() * 100f;
			randomPosX *= GameManager.imageScaleRate;
			
			item.velocity = (float)Math.random() * 200f + 100f;
			item.posX = randomPosX;
			item.posY = -10 * GameManager.imageScaleRate;
			
		}
	}
	
	private int GetChoicedItemNumber()
	{
		float totalProbability = 0;
		for(int i = 0 ; i < itemProbabilities.length ; i++)
		{
			totalProbability += itemProbabilities[i];
		}
		
		float randomNumber = (float)(Math.random() * totalProbability);
		int choicedItemNumber = 0;
		
		for(int i = 0; i < itemCount ; i++)
		{
			if(randomNumber <= itemProbabilities[i])
			{
				choicedItemNumber = i;
				break;
			}
			randomNumber -= itemProbabilities[i];
		}
		
		return choicedItemNumber;
	}
	
	
	public Item GetItemFromObjectPool(int itemNumber)
	{
		//아이템을 
		Item item = itemPoolQueue[itemNumber].poll();			//아이템을 저장하는 풀에서 아이템을 꺼내옴
		item.SetActivatedItemId(NextActivatedItemId());			//아이템의 ID값 부여
		activatedItemList.add(item);							//현재 맵에 보이는 활성화된 아이템들을 관리하는 리스트
		
		if(item.itemNumber == 5)
		{
			System.out.println("FastItem 꺼내옴!");
		}
		return item;
	}
	
	
	public void ReturnItemToObjectPool(Item item)
	{
		item.SetItemPosition(10000, 10000);
		
		//활성화된 아이템 리스트 중에서 자신의 아이템을 찾아서 제거한다.
		for(int i = 0 ; i < activatedItemList.size() ; i++)
		{
			if(item.GetActivatedItemId() == activatedItemList.get(i).GetActivatedItemId())
			{
				itemPoolQueue[item.itemNumber].add(item);
				//System.out.println("activatedItemList 사이즈 : " + activatedItemList.size());
				activatedItemList.remove(i);
				break;
			}
			
		}
	}
	
	
	private int NextActivatedItemId()
	{
		activatedItemId++;
		if(activatedItemId >= 1000)		//활성화된 아이템 id는 0~999까지 부여함
		{
			activatedItemId = 0;
		}
		
		return activatedItemId;
	}
	
	
	//메인씬에서 부를꺼임
	public void SetAllActivatedItemNextPosition()
	{
		for(int i = 0 ; i < activatedItemList.size(); i++)
		{
			activatedItemList.get(i).SetNextPosition();
		}
	}
	
	//모든 활성화된 아이템들을 검사. 바닥에 닿거나 햄스터에게 닿았는지 검사!!
	public void CheckAllActivatedItemTouch()
	{
		Item item;
		for(int i = 0 ; i < activatedItemList.size(); i++)
		{
			item = activatedItemList.get(i);
			if(item.IsTouchedBottom() == true)
			{
				ReturnItemToObjectPool(item);
				break;
			}
			
			if(item.IsTouchedHamster() == true)
			{
				item.SetItemEffect();		//각각의 아이템들 마다 효과가 다르므로 자식 클래스에 재정의 해둠
				ReturnItemToObjectPool(item);
				break;
			}
			
		}
	}
	
	public void SetSpeedItem()
	{
		SetSpeedArrowShaking();
		
		if(fastItemTime > 0f && slowItemTime <= 0f)
		{
			fastItemTime -= GameManager.deltaTime;
			HamsterManager.instance.velocity = HamsterManager.instance.fastVelocity;
			HamsterManager.instance.walkTime = 0.05f;
			SetFastArrow();
			
		}
		else if(slowItemTime > 0f && fastItemTime <= 0f)
		{
			slowItemTime -= GameManager.deltaTime;
			HamsterManager.instance.velocity = HamsterManager.instance.slowVelocity;
			HamsterManager.instance.walkTime = 0.35f;
			SetSlowArrow();
		}
		else
		{
			HamsterManager.instance.velocity = HamsterManager.instance.normalVelocity;
			HamsterManager.instance.walkTime = 0.1f;
			HideSpeedArrow();
		}
	}
	
	
	float fastArrowDefaultTime = 0.3f;
	float fastArrowTime = fastArrowDefaultTime;
	float fastArrowState = 1;
	
	float slowArrowDefaultTime = 0.3f;
	float slowArrowTime = fastArrowDefaultTime;
	float slowArrowState = 1;
	
	void SetFastArrow()
	{
		speedArrow.fastArrowPosX = HamsterManager.instance.GetHamsterPositionX() + HamsterManager.instance.GetHamsterScaleX();
		speedArrow.slowArrowPosX = 10000f;
	}
	
	void SetSlowArrow()
	{
		speedArrow.fastArrowPosX = 10000f;
		speedArrow.slowArrowPosX = HamsterManager.instance.GetHamsterPositionX() + HamsterManager.instance.GetHamsterScaleX();
	}
	
	void HideSpeedArrow()
	{
		speedArrow.fastArrowPosX = 10000f;
		speedArrow.slowArrowPosX = 10000f;
	}
	
	void SetSpeedArrowShaking()
	{
		fastArrowTime -= GameManager.deltaTime;
		if(fastArrowTime <= 0f)
		{
			if(fastArrowState == 1)
			{
				fastArrowTime = fastArrowDefaultTime;
				fastArrowState = 2;
				speedArrow.fastArrowPosY = HamsterManager.instance.GetHamsterPositionY() - 2 * GameManager.imageScaleRate + HamsterManager.instance.GetHamsterScaleY()/2 - speedArrow.scaleY / 2f;
			}
			else
			{
				fastArrowTime = fastArrowDefaultTime;
				fastArrowState = 1;
				speedArrow.fastArrowPosY = HamsterManager.instance.GetHamsterPositionY() + 2 * GameManager.imageScaleRate + HamsterManager.instance.GetHamsterScaleY()/2 - speedArrow.scaleY / 2f;
			}
			
		}
		
		slowArrowTime -= GameManager.deltaTime;
		if(slowArrowTime <= 0f)
		{
			if(slowArrowState == 1)
			{
				slowArrowTime = slowArrowDefaultTime;
				slowArrowState = 2;
				speedArrow.slowArrowPosY = HamsterManager.instance.GetHamsterPositionY() - 2 * GameManager.imageScaleRate + HamsterManager.instance.GetHamsterScaleY()/2 - speedArrow.scaleY / 2f;
			}
			else
			{
				slowArrowTime = slowArrowDefaultTime;
				slowArrowState = 1;
				speedArrow.slowArrowPosY = HamsterManager.instance.GetHamsterPositionY() + 2 * GameManager.imageScaleRate + HamsterManager.instance.GetHamsterScaleY()/2 - speedArrow.scaleY / 2f;
			}
			
		}
	}
	
	
}