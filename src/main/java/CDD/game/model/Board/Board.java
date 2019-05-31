package CDD.game.model.Board;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.Player.*;
import CDD.game.model.Round.Round;

public class Board {

	private Player leftPlayer;
	private Player rightPlayer;
	private Player topPlayer;
	private Player bottomPlayer;
	
	private Round currentRound;
	
	private Player currentPlayer;
	
	private CardGroup currentGroups;
	
	private boolean flag;
	
	public void changeTurn(Round round)
	{
		currentRound = round;
	}
	
	public void showCards(List<Card> cards)
	{
		currentRound.showCards(cards, this);
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
		System.out.println(currentGroups.getCards().size());
	}

	public Player getLeftPlayer() {
		return leftPlayer;
	}

	public void setLeftPlayer(Player leftPlayer) {
		this.leftPlayer = leftPlayer;
	}

	public Player getRightPlayer() {
		return rightPlayer;
	}

	public void setRightPlayer(Player rightPlayer) {
		this.rightPlayer = rightPlayer;
	}

	public Player getTopPlayer() {
		return topPlayer;
	}

	public void setTopPlayer(Player topPlayer) {
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
