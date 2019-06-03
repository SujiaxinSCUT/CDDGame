package CDD.game.model.Card;

import java.util.List;

import CDD.game.model.CardGroup.CardType;

public class Filter {

	public static boolean DiamondThreejudge(List<Card> cards) {
		for (Card card : cards) {
			if (card.getPoint() == 1) {
				if (card.getColor() == card.Diamond) {
					return true;
				}
			}
		}
		return false;
	}
	

}
