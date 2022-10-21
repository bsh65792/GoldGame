package GoldGame;

public class HamsterManager
{
	public static HamsterManager instance;
	
	public boolean isReverseHamster;
	private float posX;
	private final float posY;
	
	private final float scaleX;
	private final float scaleY;
	
	private float velocity;
	
	private int walkAnimationNumber = 1;
	private float nowWalkTime;
	private final float walkTime = 0.1f;
	
	HamsterManager()
	{
		instance = this;
		isReverseHamster = false;
		
		
		//처음 위치와 크기 지정
		posX = 40 * GameManager.imageScaleRate;
		posY = 190 * GameManager.imageScaleRate;
		
		scaleX = 45 * GameManager.imageScaleRate * 0.5f;
		scaleY = 47 * GameManager.imageScaleRate * 0.5f;
		
		//햄스터 이동 속도. 1초당 140픽셀씩 이동한다. float로 한 이유는 10프레임에 1픽셀이 이동하거나 할 경우도 있기 때문이고, 화면에 그려줄 때는 int로 캐스팅함.
		velocity = 210f;
		nowWalkTime = walkTime;
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
	
	//왼쪽키만 누르고 있는 동안에는 매 프레임마다 호출될 예정임
	public void MoveLeft()
	{
		posX -= GameManager.instance.deltaTime * velocity;		//왼쪽으로 눈꼽만큼 움직임. but 매 프레임마다 움직이는 것이므로 많이 움직임!
		
		//햄스터가 왼쪽으로 나가지 않도록!!
		if(posX < 0)
		{
			posX = 0;
		}
		
//		GameViewManager.instance.DrawHamster();   삭제 main에서 repaint() 호출
	}
	
	public void MoveRight()
	{
		posX += GameManager.instance.deltaTime * velocity;
		
		//햄스터가 맵의 오른쪽으로 나가지 않도록!!
		if(posX > GameManager.imageScaleRate * 120 - scaleX)
		{
			posX = GameManager.imageScaleRate * 120 - scaleX;
		}
//		GameViewManager.instance.DrawHamster();   삭제 main에서 repaint() 호출
	}
	
	public void SetWalkAnimation()
	{
		if(InputManager.instance.isLeftPressed == InputManager.instance.isRightPressed)
		{
			GameViewManager.instance.SetHamsterIdleImage();
			nowWalkTime = 0f;
			return;
		}
		
		nowWalkTime -= GameManager.deltaTime;
		
		if(nowWalkTime <= 0f)
		{
			nowWalkTime = walkTime;
			
			if(walkAnimationNumber == 1)
			{
				GameViewManager.instance.SetHamsterWalk_1Image();
				walkAnimationNumber = 2;
			}
			else if(walkAnimationNumber == 2)
			{
				GameViewManager.instance.SetHamsterWalk_2Image();
				walkAnimationNumber = 1;
			}
			
		}
		
		
	}

}
