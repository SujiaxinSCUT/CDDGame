package CDD.game.model.Round;

import java.util.List;

import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;

public class BottomRound extends Round
{

	
	
	
	@Override
	public void showCards(Board board) {
		
		board.changeTurn(new LeftRound());
		board.setCurrentPlayer(board.getLeftPlayer());
		board.showCards();
	}
	
}