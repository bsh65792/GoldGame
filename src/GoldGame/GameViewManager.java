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
	Image timePlusItemImage;
	Image fastItemImage;
	Image slowItemImage;
	
	Image fastArrowImage;
	Image slowArrowImage;
	Image descriptionImage;
	
	Image[] cloudImages = new Image[10];


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
		fastItemImage = Toolkit.getDefaultToolkit().getImage("FastItem.png");
		slowItemImage = Toolkit.getDefaultToolkit().getImage("SlowItem.png");
		timePlusItemImage = Toolkit.getDefaultToolkit().getImage("TimePlusItem.png");
		fastArrowImage = Toolkit.getDefaultToolkit().getImage("FastArrow.png");
		slowArrowImage = Toolkit.getDefaultToolkit().getImage("SlowArrow.png");
		descriptionImage = Toolkit.getDefaultToolkit().getImage("DescriptionPanel.png");
		
		for(int i = 0 ; i < 10 ; i++)
		{
			cloudImages[i] = Toolkit.getDefaultToolkit().getImage("Cloud_" + i + ".png");
		}
		

		
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
		
		for(int i = 0 ; i < CloudManager.instance.cloudList.size(); i++)
		{
			Cloud cloud = CloudManager.instance.cloudList.get(i);
			g.drawImage(cloudImages[cloud.cloudType], (int)cloud.posX, (int)cloud.posY, (int)cloud.scaleX, (int)cloud.scaleY, this);
		}
		
		
		int activatedHamsterQuantity = ItemManager.instance.activatedItemList.size();
		
		for(int i = 0 ; i < activatedHamsterQuantity ; i++)
		{
			Item item;
			
			if(ItemManager.instance.activatedItemList.get(i) == null)
			{
				continue;
			}
			
			item = ItemManager.instance.activatedItemList.get(i);
			
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
			case 4:
				g.drawImage(timePlusItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);
				break;
			case 5:
				g.drawImage(fastItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);
				break;
			case 6:
				g.drawImage(slowItemImage, (int)item.posX, (int)item.posY, (int)item.scaleX, (int)item.scaleY, this);

			default:
				break;
			}
		}
		
		if(HamsterManager.instance.isReverseHamster == true)
		{
			g.drawImage(hamster, hamsterPosX + hamsterScaleX, hamsterPosY, -hamsterScaleX, hamsterScaleY, this);
		}
		else
		{
			g.drawImage(hamster, hamsterPosX, hamsterPosY, hamsterScaleX, hamsterScaleY, this);
		}
		g.drawImage(fastArrowImage, (int)SpeedArrow.instance.fastArrowPosX, (int)SpeedArrow.instance.fastArrowPosY, (int)SpeedArrow.instance.scaleX, (int)SpeedArrow.instance.scaleY, this);
		g.drawImage(slowArrowImage, (int)SpeedArrow.instance.slowArrowPosX, (int)SpeedArrow.instance.slowArrowPosY, (int)SpeedArrow.instance.scaleX, (int)SpeedArrow.instance.scaleY, this);
		
		g.drawImage(clock, 80*GameManager.imageScaleRate, 8*GameManager.imageScaleRate, 25*GameManager.imageScaleRate, 25*GameManager.imageScaleRate, this);
		time = (float) (Math.round((TimeManager.instance.GetNowTime()) * 100) / 100.0);
		g.drawString(""+ time + "", TimeManager.instance.timePosX, TimeManager.instance.timePosY);
		g.drawString("Score : "+ ScoreManager.instance.GetScore()+ "", 10*GameManager.imageScaleRate, 22*GameManager.imageScaleRate);
		
		if(TimeManager.instance.IsFinishedGame() == true) {
			g.drawString("Game Over", 40*GameManager.imageScaleRate, 120*GameManager.imageScaleRate);
		}
		
		if(GameManager.instance.isPlayingGame == false)
		{
			int descriptionPanelX = 100 * GameManager.imageScaleRate;
			int descriptionPanelY = 160 * GameManager.imageScaleRate;
			g.drawImage(descriptionImage, 60 * GameManager.imageScaleRate - descriptionPanelX / 2, 120 * GameManager.imageScaleRate - descriptionPanelY / 2, descriptionPanelX, descriptionPanelY, this);
		}

	}
	
	public void SetHamsterIdleImage()
	{
		hamster = Toolkit.getDefaultToolkit().getImage("hamster.png");
	}
	public void SetHamsterWalk_1Image()
	{
		hamster = Toolkit.getDefaultToolkit().getImage("hamsterWalk_1.png");
	}
	public void SetHamsterWalk_2Image()
	{
		hamster = Toolkit.getDefaultToolkit().getImage("hamsterWalk_2.png");
	}
	
	
}
	
/*	public void DrawHamster()
	{
		//햄스터를 화면에 출력한다. repaint() 메소드는 JPanel에서 상속받은 듯 하며, 위에 paint() 함수의 내용을 다시 반복하는 듯.
		repaint();
	}
}
*/
