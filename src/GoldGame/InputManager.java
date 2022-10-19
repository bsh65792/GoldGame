package GoldGame;

import javax.swing.*; //윈도우창 띄울 때 사용
import java.awt.*;
import java.awt.event.*;

public class InputManager implements KeyListener
{
	public static InputManager instance;
	
	public boolean isLeftPressed = false;
	public boolean isRightPressed = false;
	
	
	InputManager()
	{
		instance = this;
	}
	
	//아래 Key~~ 메소드들은 KeyListener 인터페이스를 상속받을 때 반드시 오버라이딩 해 줘야 하는 함수들이며, 키보드 입력이 있을 때 발동되는 함수들이다.
	@Override
    public void keyTyped(KeyEvent e) 
	{
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) 				//키가 눌러지는 순간 눌러진 키 값과 함께 호출됨
    {
        // TODO Auto-generated method stub
    	
    	//눌러진 키보드 값이 왼쪽 화살표 키라면
    	if(e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		System.out.println("VK_LEFT");
    		isLeftPressed = true;					//왼쪽 눌러졌다고 필드값 수정함(매 프레임마다 main()에서 이 값을 체크해서 햄스터에게 움직여라고 명령을 내릴지 말지 판단함) 
    		
    	}
    	
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		System.out.println("VK_RIGHT");
    		isRightPressed = true;
    	}
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    	if(e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		System.out.println("VK_LEFT 떼기");
    		isLeftPressed = false;
    	}
    	
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		System.out.println("VK_RIGHT 떼기");
    		isRightPressed = false;
    	}
    }
    
    
    
    
    public void CheckKeyInput()
	{
		//System.out.println("CheckKeyInput()");
		
		if(isLeftPressed == true && isRightPressed == true)
		{
			return;
		}
		
		if(isLeftPressed == true)
		{
			//System.out.println("CheckKeyInput() -> isLeftPressed == true");
			HamsterManager.instance.MoveLeft();
		}
		
		if(isRightPressed == true)
		{
			//System.out.println("CheckKeyInput() -> isLeftPressed == false");
			HamsterManager.instance.MoveRight();
		}
		
	}
    
    
    
    
    
    
}
