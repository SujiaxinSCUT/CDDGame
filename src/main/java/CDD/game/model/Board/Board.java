package CDD.game.model.Board;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import CDD.game.controller.BoardController;
import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.Card.Filter;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.Player.*;

public class Board {

	private Robot leftPlayer;
	private Robot rightPlayer;
	private Robot topPlayer;
	private UserPlayer bottomPlayer;

	
	private  Player currentPlayer;
	
	private CardGroup currentGroups;
	
	private boolean flag;
	
	private BoardController controller;
	
	private boolean stop;
	
	
	
	public Board(UserPlayer player)
	{
		
		CardFactory.shuffle();
		this.bottomPlayer=player;
		this.leftPlayer=new LeftRobot();
		this.topPlayer=new TopRobot();
		this.rightPlayer=new RightRobot();
		bottomPlayer.setHandCards(CardFactory.getCards());
		leftPlayer.setHandCards(CardFactory.getCards());
		topPlayer.setHandCards(CardFactory.getCards());
		rightPlayer.setHandCards(CardFactory.getCards());
		if(Filter.DiamondThreejudge(bottomPlayer.getHandCards()))
		{
			currentPlayer=bottomPlayer;
			return;
		}
		if(Filter.DiamondThreejudge(leftPlayer.getHandCards()))
		{
			currentPlayer=leftPlayer;	
			return;
		}
		if(Filter.DiamondThreejudge(topPlayer.getHandCards()))
		{
			currentPlayer=topPlayer;
			return;
		}
		if(Filter.DiamondThreejudge(rightPlayer.getHandCards()))
		{
			currentPlayer=rightPlayer;
			return;
		}
		
		
	}
	
	
	
	public void RobotRun()
	{
		Thread thread1=new Thread(leftPlayer);
		Thread thread2=new Thread(rightPlayer);
		Thread thread3=new Thread(topPlayer);
		thread1.start();
		thread2.start();
		thread3.start();
	}

    public int getScore()
    {
    	if(currentPlayer!=bottomPlayer)
    	{
    		int score=0;
    		for(Card card:bottomPlayer.getHandCards())
    		{
    			score+=2;
    		}
    		return score;
    	}
    	else
    	{
    		int score=0;
    		for(Card card:topPlayer.getHandCards())
    		{
    			score+=5;
    		}
    		for(Card card:leftPlayer.getHandCards())
    		{
    			score+=5;
    		}
    		for(Card card:rightPlayer.getHandCards())
    		{
    			score+=5;
    		}
    		return score;
    	}
    }
	
	
	public boolean isStop() {
		return stop;
	}



	public void setStop(boolean stop) {
		this.stop = stop;
	}



	public void setController(BoardController controller)
	{
		this.controller=controller;
	}
    
    public synchronized void updateView() {
    	this.controller.updateView();
    }


	public synchronized Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;		
	}

	public CardGroup getCurrentGroups() {
		return currentGroups;
	}

	public void setCurrentGroups(CardGroup currentGroups) {
		this.currentGroups = currentGroups;
		
	}

	public Robot getLeftPlayer() {
		return leftPlayer;
	}

	public void setLeftPlayer(Robot leftPlayer) {
		this.leftPlayer = leftPlayer;
	}

	public Robot getRightPlayer() {
		return rightPlayer;
	}

	public void setRightPlayer(Robot rightPlayer) {
		this.rightPlayer = rightPlayer;
	}

	public Robot getTopPlayer() {
		return topPlayer;
	}

	public void setTopPlayer(Robot topPlayer) {
		this.topPlayer = topPlayer;
	}

	public Player getBottomPlayer() {
		return bottomPlayer;
	}

	public void setBottomPlayer(UserPlayer bottomPlayer) {
		this.bottomPlayer = bottomPlayer;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
		stopRobot();
		try {
			controller.gameOver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopRobot()
	{
		topPlayer.running=false;
		leftPlayer.running=false;
		rightPlayer.running=false;
	}
	
	public BoardController getController()
	{
		return controller;
	}
	
}
