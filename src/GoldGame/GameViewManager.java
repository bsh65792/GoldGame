package GoldGame;

import javax.swing.*;
import java.awt.*;

public class GameViewManager extends JPanel
{	
	public static GameViewManager instance;		//싱글턴 선언
	
	Image background;
	Image hamster;
	Image clock;
	Image coinItemImage;
	Image goldBarItemImage;
	Image minusItemImage;
	Image goldTheifItemImage;
	
	private final Font font = new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 20);
	
	public GameViewManager()
	{
		instance = this;
		
		background = Toolkit.getDefaultToolkit().getImage("background.png");
		hamster = Toolkit.getDefaultToolkit().getImage("hamster.png");
		clock = Toolkit.getDefaultToolkit().getImage("GoldStageClock.png");
		coinItemImage = Toolkit.getDefaultToolkit().getImage("Coin.png");
		goldBarItemImage = Toolkit.getDefaultToolkit().getImage("GoldBar.png");
		minusItemImage = Toolkit.getDefaultToolkit().getImage("Theif.png");
		goldTheifItemImage = Toolkit.getDefaultToolkit().getImage("GoldTheif.png");
		
		
	}
	
	//화면에 이미지들을 그리는 메소드
	public void paint(Graphics g)
	{
		//햄스터들의 좌표를 HamsterManager에서 불러옴
		int hamsterPosX = HamsterManager.instance.GetHamsterPositionX();
		int hamsterPosY = HamsterManager.instance.GetHamsterPositionY();
		int hamsterScaleX = HamsterManager.instance.GetHamsterScaleX();	
		int hamsterScaleY = HamsterManager.instance.GetHamsterScaleY();
		float time;
		g.setColor(Color.BLACK);
		g.setFont(font);
		
		//이미지를 그림
		g.drawImage(background, 0, 0, 120 * GameManager.imageScaleRate, 240 * GameManager.imageScaleRate, this);
		
		
		int activatedHamsterQuantity = ItemManager.instance.activatedItemList.size();
		
		for(int i = 0 ; i < activatedHamsterQuantity ; i++)
		{
			Item item = ItemManager.instance.activatedItemList.get(i);
			switch(item.itemNumber)
			{
			case 0:
				g.drawImage(coinItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);
				break;
			case 1:
				g.drawImage(goldBarItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);
				break;
			case 2:
				g.drawImage(minusItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);
				break;
			case 3:
				g.drawImage(goldTheifItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);
				break;
			default:
				break;
			}
		}
		
		g.drawImage(hamster, hamsterPosX, hamsterPosY, hamsterScaleX, hamsterScaleY, this);
		g.drawImage(clock, 80*GameManager.imageScaleRate, 10*GameManager.imageScaleRate, 20*GameManager.imageScaleRate, 20*GameManager.imageScaleRate, this);
		time = (float) (Math.round((TimeManager.instance.GetNowTime()) * 100) / 100.0);
		g.drawString(""+ time + "", 83*GameManager.imageScaleRate, 22*GameManager.imageScaleRate);
		g.drawString("Score : "+ ScoreManager.instance.GetScore()+ "", 10*GameManager.imageScaleRate, 22*GameManager.imageScaleRate);

	}
}
	
/*	public void DrawHamster()
	{
		//햄스터를 화면에 출력한다. repaint() 메소드는 JPanel에서 상속받은 듯 하며, 위에 paint() 함수의 내용을 다시 반복하는 듯.
		repaint();
	}
}
*/
