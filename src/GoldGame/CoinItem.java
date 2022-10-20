package GoldGame;

public class CoinItem extends Item
{
	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(1);  //coin item은 1점 추가
	}
}
