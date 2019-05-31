package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;

public class BottomRound extends Round
{

	@Override
	public void showCards(List<Card> cards, Board board) {
		if (cards!=null) {
			// TODO Auto-generated method stub
			List<Card> oldcards = board.getBottomPlayer().getHandCards();
			oldcards.removeAll(cards);
			board.getBottomPlayer().setHandCards(oldcards);
			if (oldcards.size() == 0) {
				board.setFlag(true);
				return;
			} 
		}
		board.changeTurn(new LeftRound());
		board.setCurrentPlayer(board.getLeftPlayer());
	}
	
}