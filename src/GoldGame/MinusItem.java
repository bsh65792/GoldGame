package GoldGame;

public class MinusItem extends Item
{
	MinusItem()
	{
		itemNumber = 2;
		scaleX = 13f * GameManager.imageScaleRate;
		scaleY = 13f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(-5);    //5점 마이너스
	}
}
