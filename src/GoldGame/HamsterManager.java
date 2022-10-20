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
		
		
		//처음 위치와 크기 지정
		posX = 60 * GameManager.imageScaleRate;
		posY = 200 * GameManager.imageScaleRate;
		
		scaleX = 21 * GameManager.imageScaleRate;
		scaleY = 26 * GameManager.imageScaleRate;
		
		//햄스터 이동 속도. 1초당 140픽셀씩 이동한다. float로 한 이유는 10프레임에 1픽셀이 이동하거나 할 경우도 있기 때문이고, 화면에 그려줄 때는 int로 캐스팅함.
		velocity = 140f;
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
//		GameViewManager.instance.DrawHamster();   삭제 main에서 repaint() 호출
	}
	
	public void MoveRight()
	{
		posX += GameManager.instance.deltaTime * velocity;
//		GameViewManager.instance.DrawHamster();   삭제 main에서 repaint() 호출
	}

}
