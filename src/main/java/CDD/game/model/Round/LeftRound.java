package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;
import CDD.game.model.CardGroup.CardGroup;

public class LeftRound extends Round
{

	
	
	@Override
	public void showCards(Board board) {
		
		CardGroup group=board.getLeftPlayer().showCards();
		if(group!=null)
		{
			board.setCurrentGroups(group);
		}
		board.changeTurn(new TopRound());
		board.setCurrentPlayer(board.getTopPlayer());
		board.showCards();
		board.updateView();
	}
	
}