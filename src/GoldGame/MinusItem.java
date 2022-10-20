package GoldGame;

public class MinusItem extends Item
{

	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(-5);    //5점 마이너스
	}
}
