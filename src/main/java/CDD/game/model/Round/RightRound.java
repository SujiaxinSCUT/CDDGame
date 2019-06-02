package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;
import CDD.game.model.CardGroup.CardGroup;

public class RightRound extends Round
{

	@Override
	public void showCards(Board board) {
		// TODO Auto-generated method stub
		CardGroup group=board.getRightPlayer().showCards();
		if(group!=null)
		{
			board.setCurrentGroups(group);
		}
		board.changeTurn(new BottomRound());
		board.setCurrentPlayer(board.getBottomPlayer());
		
		board.updateView();
	}
	
}
