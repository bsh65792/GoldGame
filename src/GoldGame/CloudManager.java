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
	
	
	public void SetCloud()
	{
		TryCreateCloud();
		MoveAllCloud();
		FindAllShouldRemoveCloud();
	}
	
	void MoveAllCloud()
	{
		for(int i = 0 ; i < cloudList.size() ; i++)
		{
			cloudList.get(i).MoveRight();
		}
	}
	
	public void FindAllShouldRemoveCloud()
	{
		int cloudQuantity = cloudList.size();
		Stack<Integer> removeIndexStack = new Stack<Integer>();
		Cloud cloud;
		
		//현재 떠 있는 구름들 중에 제거하고자 하는 구름들을 모두 removeIndexStack에 넣는다.
		for(int i = 0 ; i < cloudQuantity; i++)
		{
			if(cloudList.get(i).ShouldRemoveCloud() == true)
			{
				removeIndexStack.add(i);
			}
		}
		
		//구름들 제거. 오름차순으로 넣었기 떄문에 FILO에 의해 가장 높은 인덱스 부터 삭제될 것이므로 index가 밀리는 문제가 발생되지 않는다.
		while(removeIndexStack.size() > 0)
		{
			cloudList.get(removeIndexStack.pop());
		}

	}
	
	public void TryCreateCloud()
	{
		remainCloudTime -= GameManager.deltaTime;
		
		if(remainCloudTime <= 0f)
		{	
			remainCloudTime = (float)(Math.random() * 1.5f + 0.5f);		//2초 ~ 5초에 1번 구름이 나타남
			Cloud cloud = new Cloud();
			cloud.velocity = (int)Math.random() * 40 + 40;			//50 ~ 100 사이
			cloud.posX = -cloud.scaleX;
			cloud.posY = (float)Math.random() * (200 - cloud.scaleY / GameManager.imageScaleRate) * GameManager.imageScaleRate;
			cloud.cloudType = (int)((float)Math.random() * 9.9999f);
			cloudList.add(cloud);
			
		}
	}
	
	
}
