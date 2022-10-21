package GoldGame;

public class GoldBarItem extends Item
{

	GoldBarItem()
	{
		itemNumber = 1;
		scaleX = 13f * GameManager.imageScaleRate * 0.8f;
		scaleY = 10f * GameManager.imageScaleRate * 0.8f;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(5);     // 5점 추가
	}
}
