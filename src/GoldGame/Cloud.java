package GoldGame;

public class Cloud
{
	public float velocity;
	public float posX;
	public float posY;
	public float scaleX;
	public float scaleY;
	
	
	public int cloudType;
	
	Cloud()
	{
		scaleX = 50 * GameManager.imageScaleRate;
		scaleY = 50 * GameManager.imageScaleRate;
	}
	
	
	public void MoveRight()
	{
		posX = posX + GameManager.deltaTime * velocity;
	}
	
	public boolean ShouldRemoveCloud()
	{
		if(posX >= 120 * GameManager.imageScaleRate)
		{
			return true;
		}
		
		return false;
	}
	
}
