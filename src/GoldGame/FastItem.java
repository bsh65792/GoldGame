package GoldGame;

public class FastItem extends Item
{
	FastItem()
	{
		itemNumber = 0;
		scaleX = 10f * GameManager.imageScaleRate;
		scaleY = 10f * GameManager.imageScaleRate;
	}
	
	
	public void SetItemEffect()
	{
		ItemManager.instance.fastItemTime = 5f;
		ItemManager.instance.slowItemTime = -1f;
	}
	
	
	
}
