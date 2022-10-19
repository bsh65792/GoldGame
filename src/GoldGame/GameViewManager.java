package GoldGame;

import javax.swing.*;
import java.awt.*;

public class GameViewManager extends JPanel
{	
	public static GameViewManager instance;		//싱글턴 선언
	Image background;
	Image hamster;
	
	
	public GameViewManager()
	{
		instance = this;
		
		background = Toolkit.getDefaultToolkit().getImage("background.png");
		hamster = Toolkit.getDefaultToolkit().getImage("hamster.png");
		
	}
	
	public void paint(Graphics g)
	{
		int hamsterPosX = HamsterManager.instance.GetHamsterPositionX();
		int hamsterPosY = HamsterManager.instance.GetHamsterPositionY();
		int hamsterScaleX = HamsterManager.instance.GetHamsterScaleX();
		int hamsterScaleY = HamsterManager.instance.GetHamsterScaleY();
		
		g.drawImage(background, 0, 0, 120 * GameManager.imageScaleRate, 240 * GameManager.imageScaleRate, this);
		g.drawImage(hamster, hamsterPosX, hamsterPosY, hamsterScaleX, hamsterScaleY, this);
	}
	
	public void SetHamsterPosition()
	{
		repaint();
	}
}
