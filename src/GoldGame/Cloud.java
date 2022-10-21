package GoldGame;

public class Cloud
{
	public float velocity;
	public float posX;
	public float posY;
	public float scaleX;
	public float scaleY;
	
	
	public int cloudType;
	
	
	
	public void MoveRight()
	{
		posX = posX + GameManager.deltaTime * velocity;
	}
	
	public boolean ShouldRemoveCloud()
	{
		if(posX >= 240 * GameManager.imageScaleRate)
		{
			return true;
		}
		
		return false;
	}
	
}
