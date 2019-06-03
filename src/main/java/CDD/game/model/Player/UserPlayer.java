package CDD.game.model.Player;

import CDD.game.model.Board.Board;
import CDD.game.model.CardGroup.CardGroup;

public class UserPlayer extends Player{

	public void showCards(Board board,CardGroup group)
    {
    	if(group!=null)
    	{
    		board.setCurrentGroups(group);
    		this.getHandCards().removeAll(group.getCards());
    	}
    	
    	board.setCurrentPlayer(board.getLeftPlayer());
    }
}
