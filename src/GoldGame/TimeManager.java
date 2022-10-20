package GoldGame;

public class TimeManager
{
	private float nowTime;
	
	public static TimeManager instance;
	
	TimeManager()
	{
		instance = this;
		nowTime = 50;
	}
	
	public void AddNowTime(float time)
	{
		nowTime -= time;
	}
	
	
	public float GetNowTime()
	{
		return nowTime;
	}
	
	
	public boolean IsFinishedGame()
	{
		if(nowTime <= 0)
			return true;
		
		else
			return false;
	}

}
