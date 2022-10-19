package GoldGame;

public class HamsterManager
{
	public static HamsterManager instance;
	
	private float posX;
	private final float posY;
	
	private final float scaleX;
	private final float scaleY;
	
	private float velocity;
	
	HamsterManager()
	{
		instance = this;
		
		posX = 60 * GameManager.imageScaleRate;
		posY = 200 * GameManager.imageScaleRate;
		
		scaleX = 21 * GameManager.imageScaleRate;
		scaleY = 26 * GameManager.imageScaleRate;
		
		velocity = 60f;
	}
	
	public int GetHamsterPositionX()
	{
		return (int)posX;
	}
	
	public int GetHamsterPositionY()
	{
		return (int)posY;
	}
	
	public int GetHamsterScaleX()
	{
		return (int)scaleX;
	}
	
	public int GetHamsterScaleY()
	{
		return (int)scaleY;
	}
	
	public void MoveLeft()
	{
		posX -= GameManager.instance.deltaTime;
		GameViewManager.instance.SetHamsterPosition();
	}
	
	public void MoveRight()
	{
		
	}

}
