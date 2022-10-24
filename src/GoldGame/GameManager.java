package GoldGame;

import javax.swing.*; //윈도우창 띄울 때 사용
import java.awt.*;
import java.awt.event.*;

public class GameManager extends JFrame
{
	public static GameManager instance;				//싱글턴 패턴 적용(GameManager에서 생성한 gameManager 객체는 단 하나만 존재하며, 그 객체를 static 변수로 지정하면 어떠한 곳에서도 그 객체를 가리킬 수 있음!)
	public static final float deltaTime = 1f/120f;	//한 frame당 흘러간 시간
	
	public static int imageScaleRate = 4;			//image 이동 및 배치, 크기조절 등에 모두 곱해주어서 혹시라도 화면크기를 조정해야 할 때 해당 변수 하나만 바꿔줄 수 있도록 static으로 지정함
	
	public boolean isPlayingGame = false;
	public boolean isStop = false;
	public boolean isRestartGame = false;
	public boolean isExitGame = false;
	
	//아래 클래스들은 이 sw에서 단 하나의 객체만 생성될 것이므로 gameManager 인스턴스 생성할 때 같이 생성해줌
	GameViewManager gameViewManager;
	HamsterManager hamsterManager;
	InputManager inputManager;
	ItemManager itemManager;
	TimeManager timeManager;
	ScoreManager scoreManager;
	SoundManager soundManager;
	CloudManager cloudManager;
	
	//public JButton startButton;
	//private int startButtonScaleX;
	//private int startButtonScaleY;
	
	public GameManager()
	{
		instance = this;
		
		//setPreferredSize(new Dimension(120 * imageScaleRate, 240 * imageScaleRate));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameViewManager = new GameViewManager();
		hamsterManager = new HamsterManager();
		inputManager = new InputManager();
		itemManager = new ItemManager();
		timeManager = new TimeManager();
		scoreManager = new ScoreManager();
//		soundManager = new SoundManager();
		cloudManager = new CloudManager();
		
		//startButton = new JButton("게임 시작");
		//startButtonScaleX = 30 * imageScaleRate;
		//startButtonScaleY = 15 * imageScaleRate;
		
		//startButton.setLocation(60 * imageScaleRate - startButtonScaleX / 2, 120 * imageScaleRate - startButtonScaleY / 2);
		//startButton.setSize(startButtonScaleX, startButtonScaleY);
		
		//아래 함수들은 JFrame에서 상속받은 함수로, 화면을 만들어 주는 함수인듯ㅋ
		add("Center", gameViewManager);
		//startButton.addActionListener(new StartButtonListener());
		//add(startButton, "South");
		
		setSize(124 * imageScaleRate, 250 * imageScaleRate);
		setVisible(true);
		addKeyListener(inputManager);
		//(new CheckInputThread()).start();
//    	soundManager.abc();
		
	}
	
	
	public static void main(String[] args)
	{
		
		while(true)
		{
			GameManager gameManager = new GameManager();
			
			while(true)
			{
				if(GameManager.instance.isPlayingGame == true)
				{
					break;
				}
				

				try
				{
					Thread.sleep((int)(1000f * deltaTime));
				}
				catch(Exception e)
				{
					
				}	
			}
			
			
			while(true)
			{
				
				//Pause가 눌러졌는지 확인
				if(GameManager.instance.isStop == true)
				{
					GameViewManager.instance.repaint();
					try
					{
						Thread.sleep((int)(1000f * deltaTime));
					}
					catch(Exception e)
					{
						
					}
					continue;
				}
				
				
				//키보드 인풋 입력을 확인
				InputManager.instance.CheckKeyInput();
				
				//남은 시간 관련
				TimeManager.instance.AddNowTime(deltaTime);
				TimeManager.instance.SetTimePosition();
				
				//아이템 생성, 아이템 위치 변경, 충돌감지 & 효과발동 등
				ItemManager.instance.TrySetItem();
				ItemManager.instance.SetAllActivatedItemNextPosition();
				ItemManager.instance.CheckAllActivatedItemTouch();
				ItemManager.instance.SetSpeedItem();
				
				HamsterManager.instance.SetWalkAnimation();
				
				CloudManager.instance.SetCloud();
				
				if(TimeManager.instance.IsFinishedGame() == true) {
					GameViewManager.instance.repaint();
					break;
				}
						
				
				
				GameViewManager.instance.repaint();
				
				//한 프레임을 지정해 주기 위해 deltaTime만큼 잠깐 스레드를 중지시킨다.(왜 try catch문을 써야 하는지는 모르겠음)
				try
				{
					Thread.sleep((int)(1000f * deltaTime));
				}
				catch(Exception e)
				{
					
				}
				
				
			}
			
			while(true)
			{
				if(GameManager.instance.isRestartGame == true)
				{
					GameManager.instance.setVisible(false);
					break;
				}
				if(GameManager.instance.isExitGame == true)
				{
					System.exit(0);
				}
				
				try
				{
					Thread.sleep((int)(1000f * deltaTime));
				}
				catch(Exception e)
				{
					
				}
			}
		}
		
		
		
	}
	
}









