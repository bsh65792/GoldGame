package GoldGame;

public class ScoreManager {
	private int score;
	
	public static ScoreManager instance;
	
	ScoreManager()
	{
		instance = this;
		score = 0;
	}
	
	public void AddScore(int x) 
	{	
		score += x;
	}
	
	public int GetScore()
	{
		return score;
	}
}
