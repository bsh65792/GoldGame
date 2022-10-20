package GoldGame;

public class CoinItem extends Item
{

	CoinItem()
	{
		scaleX = 10f * GameManager.imageScaleRate;
		scaleY = 10f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(1);  //coin item은 1점 추가
	}
}
