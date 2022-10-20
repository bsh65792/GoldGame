package GoldGame;

public class GoldBarItem extends Item
{	
	public void SetItemEffect()
	{
		ScoreManager.instance.AddScore(5);     // 5점 추가
	}
}
