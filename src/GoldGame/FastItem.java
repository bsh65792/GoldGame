package GoldGame;

public class FastItem extends Item
{
	FastItem()
	{
		itemNumber = 5;
		scaleX = 8f * GameManager.imageScaleRate;
		scaleY = 8f * GameManager.imageScaleRate;
	}
	
	
	public void SetItemEffect()
	{
		ItemManager.instance.fastItemTime = 5f;
		ItemManager.instance.slowItemTime = -1f;
	}
	
	
	
}
