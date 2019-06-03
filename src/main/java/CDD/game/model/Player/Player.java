package CDD.game.model.Player;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;
import CDD.game.model.CardGroup.CardGroup;

public abstract class Player {

	List<Card> handCards;

	public List<Card> getHandCards() {
		return handCards;
	}

	public void setHandCards(List<Card> handCards) {
		this.handCards = handCards;
	}
	



}
