package GoldGame;

public abstract class Item
{
	private int activatedItemId;
	
	private float velocity;
	private float posX;
	private float posY;
	
	
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
	
	public void SetNextPosition()
	{
		posY = posY + GameManager.instance.deltaTime * velocity;
	}
	
	public boolean IsTouchedBottom()
	{
		if(posY >= 230f)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean IsTouchedHamster()
	{
		float hamsterPositionX = HamsterManager.instance.GetHamsterPositionX();
		float hamsterPositionY = HamsterManager.instance.GetHamsterPositionY();
		
		
		//y축 계산
		if(hamsterPositionY <= posY && posY <= hamsterPositionY + 5f * GameManager.deltaTime)
		{
			//x축 계산
			if(hamsterPositionX <= posX && posX <= hamsterPositionX + 5f * GameManager.deltaTime)
			{
				return true;
			}
		}
		
		return false;
	}
	
}
