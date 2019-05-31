package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;

public class LeftRound extends Round
{

	@Override
	public void showCards(List<Card> cards, Board board) {
		if (cards!=null) {
			// TODO Auto-generated method stub
			List<Card> oldCards = board.getLeftPlayer().getHandCards();
			oldCards.removeAll(cards);
			board.getLeftPlayer().setHandCards(oldCards);
			if (oldCards.size() == 0) {
				board.setFlag(true);
				return;
			} 
		}
		board.changeTurn(new TopRound());
		board.setCurrentPlayer(board.getTopPlayer());
	}
	
}