package GoldGame;

public class TimeManager
{
	private float nowTime;
	
	public static TimeManager instance;
	
	public int timePosX;
	public int timePosY;
	
	TimeManager()
	{
		instance = this;		
		nowTime = 50;
		timePosX = 83 * GameManager.imageScaleRate;
		timePosY = 23 * GameManager.imageScaleRate;
	}
	
	public void AddNowTime(float time)
	{
		if(nowTime <= 0) {
			nowTime = 0;
			return;
		}
		else{
			nowTime -= time;
		}
	}
	
	public void SetTimePosition()
	{
		if(nowTime >= 10f)
		{
			timePosX = 85 * GameManager.imageScaleRate;
		}
		else if(nowTime >0f)
		{
			timePosX = 87 * GameManager.imageScaleRate;
		}
		else
		{
			timePosX = 89 * GameManager.imageScaleRate;
		}
	}
	
	
	public float GetNowTime()
	{
		return nowTime;
	}
	
	
	public boolean IsFinishedGame()
	{
		if(nowTime == 0)
			return true;
		
		else
			return false;
	}
}
