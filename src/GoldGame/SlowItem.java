package GoldGame;

public class SlowItem extends Item
{
	
	SlowItem()
	{
		itemNumber = 0;
		scaleX = 10f * GameManager.imageScaleRate;
		scaleY = 10f * GameManager.imageScaleRate;
	}
	
	
	public void SetItemEffect()
	{
		ItemManager.instance.slowItemTime = 5f;
		ItemManager.instance.fastItemTime = -1f;
	}
	
}
