package GoldGame;

public abstract class Item
{
	private int activatedItemId;
	
	public float velocity;
	public float posX;
	public float posY;
	
	public float scaleX;
	public float scaleY;
	
	
	public int itemNumber;
	
	
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
		if(posY >= 220f * GameManager.instance.imageScaleRate)
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
		float hamsterScaleX = HamsterManager.instance.GetHamsterScaleX();
		
		float centerPosY = posY + scaleY / 2f;
		float centerPosX = posX + scaleX / 2f;
		
		//y축 계산
		if(hamsterPositionY <= centerPosY && centerPosY <= hamsterPositionY + 2f * GameManager.imageScaleRate)
		{
			//x축 계산
			if(hamsterPositionX <= centerPosX && centerPosX <= hamsterPositionX + hamsterScaleX)
			{
				return true;
			}
		}
		
		return false;
	}
	
}
