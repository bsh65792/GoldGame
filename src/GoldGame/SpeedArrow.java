package GoldGame;

public class SpeedArrow
{
	public static SpeedArrow instance;
	
	public float fastArrowPosX;
	public float fastArrowPosY;
	
	public float slowArrowPosX;
	public float slowArrowPosY;
	
	public float scaleX;
	public float scaleY;
	
	SpeedArrow()
	{
		instance = this;
		
		fastArrowPosX = 10000;
		fastArrowPosY = 10000;
		
		slowArrowPosX = 10000;
		slowArrowPosY = 10000;
		
		scaleX = 16 * GameManager.imageScaleRate * 0.8f;
		scaleY = 22 * GameManager.imageScaleRate * 0.8f;
	}
}
