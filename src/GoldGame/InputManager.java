package GoldGame;

import javax.swing.*; //윈도우창 띄울 때 사용
import java.awt.*;
import java.awt.event.*;

public class InputManager implements KeyListener
{
	public static boolean isLeftPressed = false;
	public static boolean isRightPressed = false;
	
	@Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    	
        if(e.getSource()==this)
        {
        	if(e.getKeyCode() == KeyEvent.VK_LEFT)
        	{
        		System.out.println("VK_LEFT");
        		isLeftPressed = true;
        		
        	}
        	
        	if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        	{
        		System.out.println("VK_RIGHT");
        		isRightPressed = true;
        	}
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
}
