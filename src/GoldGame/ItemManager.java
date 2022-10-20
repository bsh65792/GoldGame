package GoldGame;
import java.util.*;

public class ItemManager
{
	
	private final int itemCount;
	private Queue<Item>[] itemPoolQueue;
	private int activatedItemId = -1;
	private ArrayList<Item> activatedItemList = new ArrayList<Item>();
	
	ItemManager()
	{
		itemCount = 3;
		itemPoolQueue = new Queue[itemCount];
		
		for(int i = 0 ; i < itemCount ; i++)
		{
			itemPoolQueue[i] = new LinkedList<Item>();
		}
		
		for(int i = 0 ; i < 50 ; i++)
		{
			itemPoolQueue[0].add(new CoinItem());
			itemPoolQueue[1].add(new GoldBarItem());
			itemPoolQueue[2].add(new MinusItem());
		}
				
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
				break;
			}
			
		}
	}
	
	
}