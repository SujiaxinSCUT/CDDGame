package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;

public class RightRound extends Round
{

	@Override
	public void showCards(List<Card> cards, Board board) {
		// TODO Auto-generated method stub
		if (cards!=null) {
		List<Card> oldCards=board.getRightPlayer().getHandCards();
		oldCards.removeAll(cards);
		board.getRightPlayer().setHandCards(oldCards);
		if(oldCards.size()==0)
		{
			board.setFlag(true);
			return;
		}
		}
		board.changeTurn(new BottomRound());
		board.setCurrentPlayer(board.getBottomPlayer());
	}
	
}
