package GoldGame;

public abstract class Item
{
	private int activatedItemId;
	
	private int velocity;
	private int posX;
	private int posY;
	
	
	public abstract void SetItemEffect();
	
	public void SetItemPosition(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	public int GetActivatedItemId()
	{
		return activatedItemId;
	}
	
	public void SetActivatedItemId(int activatedItemId)
	{
		this.activatedItemId = activatedItemId;
	}
	
}
