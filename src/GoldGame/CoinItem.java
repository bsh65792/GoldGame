package GoldGame;

public class CoinItem extends Item
{

	CoinItem()
	{
		itemNumber = 0;
		scaleX = 8f * GameManager.imageScaleRate;
		scaleY = 8f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(1);  //coin item은 1점 추가
	}
}
