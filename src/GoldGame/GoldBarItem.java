package GoldGame;

public class GoldBarItem extends Item
{

	GoldBarItem()
	{
		scaleX = 13f * GameManager.imageScaleRate;
		scaleY = 10f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(5);     // 5점 추가
	}
}
