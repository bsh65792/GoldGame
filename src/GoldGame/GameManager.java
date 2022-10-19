package GoldGame;

import javax.swing.*; //윈도우창 띄울 때 사용
import java.awt.*;
import java.awt.event.*;

public class GameManager extends JFrame
{
	public static GameManager instance;
	public static final float deltaTime = 1f/60f;
	
	public static int imageScaleRate = 3;
	GameViewManager gameViewManager;
	
	public GameManager()
	{
		instance = this;
		
		gameViewManager = new GameViewManager();
		add("Center", gameViewManager);
		setSize(120 * imageScaleRate, 240 * imageScaleRate);
		setVisible(true);
	}
	
	
	public void CheckKeyInput()
	{
		if(InputManager.isLeftPressed == true && InputManager.isRightPressed == true)
		{
			return;
		}
		
		if(InputManager.isLeftPressed == true)
		{
			
		}
		
	}
	
	
	
	public static void main(String[] args)
	{
		GameManager gameManager = new GameManager();
		int count = 0;
		
		while(true)
		{
			count++;
			System.out.println(count + "번쨰 frame");
			
			try
			{
				Thread.sleep((int)(1000f * 1f / 60f));
			}
			catch(Exception e)
			{
				
			}
			
			
		}
	}
	
}
