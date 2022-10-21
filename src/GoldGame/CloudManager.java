package GoldGame;

import java.util.*;

public class CloudManager
{
	public static CloudManager instance;
	private float remainCloudTime;
	
	public ArrayList<Cloud> cloudList = new ArrayList<Cloud>();
	
	CloudManager()
	{
		instance = this;
		remainCloudTime = 0f;
	}
	
	
	public void TryCreateCloud()
	{
		remainCloudTime -= GameManager.deltaTime;
		
		if(remainCloudTime <= 0f)
		{
			remainCloudTime = (float)(Math.random() * 3f + 2f);		//2초 ~ 5초에 1번 구름이 나타남
			Cloud cloud = new Cloud();
			cloud.velocity = (int)Math.random() * 50 + 50;			//50 ~ 100 사이
			cloud.posX = -cloud.scaleX;
			cloud.posY = (float)Math.random() * 240 * GameManager.imageScaleRate - cloud.scaleY;
			cloudList.add(cloud);
			
		}
	}
	
	
}
