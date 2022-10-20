package GoldGame;

public class MinusItem extends Item
{
	MinusItem()
	{
		scaleX = 10f * GameManager.imageScaleRate;
		scaleY = 10f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(-5);    //5점 마이너스
	}
}
