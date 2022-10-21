package GoldGame;

public class MinusItem extends Item
{
	MinusItem()
	{
		itemNumber = 2;
		scaleX = 13f * GameManager.imageScaleRate * 0.8f;
		scaleY = 13f * GameManager.imageScaleRate * 0.8f;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(-5);    //5점 마이너스
	}
}
