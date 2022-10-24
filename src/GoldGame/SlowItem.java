package GoldGame;

public class SlowItem extends Item
{
	
	SlowItem()
	{
		itemNumber = 6;
		scaleX = 8f * GameManager.imageScaleRate;
		scaleY = 8f * GameManager.imageScaleRate;
	}
	
	
	public void SetItemEffect()
	{
		ItemManager.instance.slowItemTime = 5f;
		ItemManager.instance.fastItemTime = -1f;
	}
	
}
