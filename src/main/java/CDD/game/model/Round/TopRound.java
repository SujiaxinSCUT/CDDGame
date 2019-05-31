package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;

public class TopRound extends Round
{

	@Override
	public void showCards(List<Card> cards, Board board) {
		// TODO Auto-generated method stub
		if (cards!=null) {
		List<Card> oldCards=board.getTopPlayer().getHandCards();
		oldCards.removeAll(cards);
		board.getTopPlayer().setHandCards(oldCards);
		if(oldCards.size()==0)
		{
			board.setFlag(true);
			return;
		}
		}
	    board.changeTurn(new RightRound());
	    board.setCurrentPlayer(board.getRightPlayer());
    }
}
