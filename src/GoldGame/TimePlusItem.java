package GoldGame;

public class TimePlusItem extends Item
{

	TimePlusItem()
	{
		itemNumber = 4;
		scaleX = 8f * GameManager.imageScaleRate;
		scaleY = 8f * GameManager.imageScaleRate;
	}

	public void SetItemEffect()
	{
		TimeManager.instance.AddNowTime(-5);
		ScoreManager.instance.AddScore(1);  //coin item은 1점 추가
	}
}