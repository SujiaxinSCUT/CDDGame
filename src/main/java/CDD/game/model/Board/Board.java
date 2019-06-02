package CDD.game.model.Board;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import CDD.game.controller.BoardController;
import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.Card.DiamondThreeFilter;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.Player.*;
import CDD.game.model.Round.BottomRound;
import CDD.game.model.Round.LeftRound;
import CDD.game.model.Round.RightRound;
import CDD.game.model.Round.Round;
import CDD.game.model.Round.TopRound;

public class Board {

	private Robot leftPlayer;
	private Robot rightPlayer;
	private Robot topPlayer;
	private Player bottomPlayer;
	
	private Round currentRound;
	
	private Player currentPlayer;
	
	private CardGroup currentGroups;
	
	private boolean flag;
	
	private BoardController controller;
	
	public void changeTurn(Round round)
	{
		currentRound = round;
	}
	
	public void showCards()
	{
		currentRound.showCards(this);
	}
	
	public Board(UserPlayer player)
	{
		
		CardFactory.shuffle();
		this.bottomPlayer=player;
		this.leftPlayer=new Robot();
		this.topPlayer=new Robot();
		this.rightPlayer=new Robot();
		bottomPlayer.setHandCards(CardFactory.getCards());
		leftPlayer.setHandCards(CardFactory.getCards());
		topPlayer.setHandCards(CardFactory.getCards());
		rightPlayer.setHandCards(CardFactory.getCards());
		if(DiamondThreeFilter.judge(bottomPlayer.getHandCards()))
		{
			currentPlayer=bottomPlayer;
			currentRound=new BottomRound();
			this.showCards();
			return;
		}
		if(DiamondThreeFilter.judge(leftPlayer.getHandCards()))
		{
			currentPlayer=leftPlayer;
			currentRound=new LeftRound();			
			return;
		}
		if(DiamondThreeFilter.judge(topPlayer.getHandCards()))
		{
			currentPlayer=topPlayer;
			currentRound=new TopRound();			
			return;
		}
		if(DiamondThreeFilter.judge(rightPlayer.getHandCards()))
		{
			currentPlayer=rightPlayer;
			currentRound=new RightRound();
			return;
		}
		
		
	}

	public void setController(BoardController controller)
	{
		this.controller=controller;
	}
    
    public void updateView() {
    	this.controller.updateView();
    }
    
	

	public Round getCurrentRound() {
		return currentRound;
	}

	public Player getCurrentPlayer() {
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

	public void setBottomPlayer(Player bottomPlayer) {
		this.bottomPlayer = bottomPlayer;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
