package GoldGame;

import javax.print.DocFlavor.URL;
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
	Image keyDescriptionImage;
	
	Image[] cloudImages = new Image[10];


	private final Font font = new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 20);
	
	public GameViewManager()
	{
		instance = this;
		
		background = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/background.png"));
		hamster = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/hamster.png"));
		clock = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/GoldStageClock.png"));
		coinItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/Coin.png"));
		goldBarItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/GoldBar.png"));
		minusItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/Theif.png"));
		goldTheifItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/GoldTheif.png"));
		fastItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/FastItem.png"));
		slowItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/SlowItem.png"));
		timePlusItemImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/TimePlusItem.png"));
		fastArrowImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/FastArrow.png"));
		slowArrowImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/SlowArrow.png"));
		descriptionImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/DescriptionPanel.png"));
		keyDescriptionImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/KeyDescriptionPanel.png"));
		
		for(int i = 0 ; i < 10 ; i++)
		{
			cloudImages[i] = Toolkit.getDefaultToolkit().getImage("src/image/Cloud_" + i + ".png");
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
			g.drawString("Game Over", 43*GameManager.imageScaleRate, 120*GameManager.imageScaleRate);
			g.drawString("Press R key : Restart Game", 27 * GameManager.imageScaleRate, 130*GameManager.imageScaleRate);
			g.drawString("Press ESC key : Exit Game", 26 * GameManager.imageScaleRate, 140*GameManager.imageScaleRate);
		}
		
		if(GameManager.instance.isPlayingGame == false)
		{
			int itemDescriptionPanelX = 90 * GameManager.imageScaleRate;
			int itemDescriptionPanelY = 150 * GameManager.imageScaleRate;
			int keyDescriptionPanelX = 90 * GameManager.imageScaleRate;
			int keyDescriptionPanelY = 60 * GameManager.imageScaleRate;
			
			g.drawImage(descriptionImage, 60 * GameManager.imageScaleRate - itemDescriptionPanelX / 2, 80 * GameManager.imageScaleRate - itemDescriptionPanelY / 2, itemDescriptionPanelX, itemDescriptionPanelY, this);
			g.drawImage(keyDescriptionImage, 60 * GameManager.imageScaleRate - keyDescriptionPanelX / 2, 190 * GameManager.imageScaleRate - keyDescriptionPanelY / 2, keyDescriptionPanelX, keyDescriptionPanelY, this);
		}
		
		if(GameManager.instance.isStop == true)
		{
			g.drawString("PAUSE ", 103 * GameManager.imageScaleRate / 2, 240 * GameManager.imageScaleRate / 2);
			g.drawString("Please push ESC key to continue", 40 * GameManager.imageScaleRate / 2, 255 * GameManager.imageScaleRate / 2);
		}

	}
	
	public void SetHamsterIdleImage()
	{
		hamster = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/hamster.png"));
	}
	public void SetHamsterWalk_1Image()
	{
		hamster = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/hamsterWalk_1.png"));
	}
	public void SetHamsterWalk_2Image()
	{
		hamster = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/hamsterWalk_2.png"));
	}
	
	
}
	
/*	public void DrawHamster()
	{
		//햄스터를 화면에 출력한다. repaint() 메소드는 JPanel에서 상속받은 듯 하며, 위에 paint() 함수의 내용을 다시 반복하는 듯.
		repaint();
	}
}
*/
