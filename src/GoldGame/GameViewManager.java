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
	
	//화면에 이미지들을 그리는 메소드
	public void paint(Graphics g)
	{
		//햄스터들의 좌표를 HamsterManager에서 불러옴
		int hamsterPosX = HamsterManager.instance.GetHamsterPositionX();
		int hamsterPosY = HamsterManager.instance.GetHamsterPositionY();
		int hamsterScaleX = HamsterManager.instance.GetHamsterScaleX();
		int hamsterScaleY = HamsterManager.instance.GetHamsterScaleY();
		
		//이미지를 그림
		g.drawImage(background, 0, 0, 120 * GameManager.imageScaleRate, 240 * GameManager.imageScaleRate, this);
		g.drawImage(hamster, hamsterPosX, hamsterPosY, hamsterScaleX, hamsterScaleY, this);
	}
	
	public void DrawHamster()
	{
		//햄스터를 화면에 출력한다. repaint() 메소드는 JPanel에서 상속받은 듯 하며, 위에 paint() 함수의 내용을 다시 반복하는 듯.
		repaint();
	}
}
