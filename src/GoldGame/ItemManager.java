package GoldGame;
import java.util.*;

public class ItemManager
{
	public static ItemManager instance;
	public ArrayList<Item> activatedItemList = new ArrayList<Item>();
	
	private final int itemCount;
	private Queue<Item>[] itemPoolQueue;
	private int activatedItemId = -1;
	
	private int coinItemProbability = 60;
	private int goldBarItemProbability = 25;
	private int minusItemProbability = 15;
	
	private float remainItemTime;
	
	
	ItemManager()
	{
		instance = this;
		
		remainItemTime = 0;
		itemCount = 3;		//아이템 총 세개임
		itemPoolQueue = new Queue[itemCount];
		
		for(int i = 0 ; i < itemCount ; i++)
		{
			itemPoolQueue[i] = new LinkedList<Item>();
		}
		
		for(int i = 0 ; i < 5000 ; i++)		//아이템을 미리 많이 만들어서 큐에 담아놓는다. 필요시 빼고 아이템이 사라져야할때 다시 넣음(오브젝트 풀링)
		{
			itemPoolQueue[0].add(new CoinItem());
			itemPoolQueue[1].add(new GoldBarItem());
			itemPoolQueue[2].add(new MinusItem());
		}
				
	}
	
	public void TrySetItem()
	{
		remainItemTime -= GameManager.deltaTime;
		
		if(remainItemTime <= 0f)
		{
			remainItemTime = 0.15f;			//아이템이 다시 떨어지기까지 걸리는 시간
			int choicedItemNumber = GetChoicedItemNumber();
			
			Item item = GetItemFromObjectPool(choicedItemNumber);
			
			
			float randomPosX = (float)Math.random() * 100f;
			randomPosX *= GameManager.imageScaleRate;
			
			item.velocity = (float)Math.random() * 60f + 140f;
			item.posX = randomPosX;
			item.posY = -10 * GameManager.imageScaleRate;
			
		}
	}
	
	private int GetChoicedItemNumber()
	{
		int randomNumber = (int)Math.random() * 100;
		int choicedItemNumber = 0;
		if(randomNumber <= coinItemProbability)
		{
			choicedItemNumber = 0;
		}
		else if(randomNumber - coinItemProbability <= goldBarItemProbability)
		{
			choicedItemNumber = 1;
		}
		else
		{
			choicedItemNumber = 2;
		}
		
		return choicedItemNumber;
	}
	
	
	public Item GetItemFromObjectPool(int itemNumber)
	{
		//아이템을 
		Item item = itemPoolQueue[itemNumber].poll();			//아이템을 저장하는 풀에서 아이템을 꺼내옴
		item.SetActivatedItemId(NextActivatedItemId());			//아이템의 ID값 부여
		activatedItemList.add(item);							//현재 맵에 보이는 활성화된 아이템들을 관리하는 리스트
		
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
				System.out.println("activatedItemList 사이즈 : " + activatedItemList.size());
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
				System.out.println("햄스터 터치");
				item.SetItemEffect();		//각각의 아이템들 마다 효과가 다르므로 자식 클래스에 재정의 해둠
				ReturnItemToObjectPool(item);
				break;
			}
			
		}
	}
	
	
}