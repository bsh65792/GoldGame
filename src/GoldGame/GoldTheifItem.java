package GoldGame;
import java.util.*;

public class GoldTheifItem extends Item
{
	GoldTheifItem()
	{
		itemNumber = 3;
		scaleX = 10f * GameManager.imageScaleRate;
		scaleY = 10f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		Item item;
		Item newItem;
		float posX;
		float posY;
		float velocity;
		
		int activatedItemQuantity = ItemManager.instance.activatedItemList.size();
		
		Queue<Item> theifQueue = new LinkedList<Item>();
		
		for(int i = 0 ; i < activatedItemQuantity ; i++)
		{
			item = ItemManager.instance.activatedItemList.get(i);
			
			if(item.itemNumber == 2)		//도둑햄구에 한해서
			{
				theifQueue.add(item);
				
			}
		}
		
		while(theifQueue.size() > 0)
		{
			item = theifQueue.poll();
			
			posX = item.posX;
			posY = item.posY;
			velocity = item.velocity;
			ItemManager.instance.ReturnItemToObjectPool(item);
			
			newItem = ItemManager.instance.GetItemFromObjectPool(1);		//코인 아이템을 가져온다.
			newItem.posX = posX;
			newItem.posY = posY;
			newItem.velocity = velocity;
		}
		
		
	}
}
