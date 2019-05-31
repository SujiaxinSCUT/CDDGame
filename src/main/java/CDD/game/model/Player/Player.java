package CDD.game.model.Player;

import java.util.List;

import CDD.game.model.Card.Card;

public abstract class Player {

	List<Card> handCards;

	public List<Card> getHandCards() {
		return handCards;
	}

	public void setHandCards(List<Card> handCards) {
		this.handCards = handCards;
	}
	
	public abstract void showCards();



}
