package CDD.game.model.Player;

import CDD.game.model.Board.Board;
import CDD.game.model.CardGroup.CardGroup;

public class UserPlayer extends Player{

	private User user;
	
	public UserPlayer(User user)
	{
		super();
		this.user=user;
	}
	
	
	
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void showCards(Board board,CardGroup group)
    {
    	if(group!=null)
    	{
    		board.setCurrentGroups(group);
    		this.getHandCards().removeAll(group.getCards());
    		if(getHandCards().size()==0)
    		{
    			board.setFlag(false);
    			return;
    		}
    	}
    	
    	board.setCurrentPlayer(board.getLeftPlayer());
    }
}
