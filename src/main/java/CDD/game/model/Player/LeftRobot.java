package CDD.game.model.Player;

import CDD.game.Game;
import CDD.game.model.Board.Board;
import CDD.game.model.CardGroup.CardGroup;
import javafx.application.Platform;

public class LeftRobot extends Robot{

	@Override
	public void changeTurn(Board board)
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        //更新JavaFX的主线程的代码放在此处
				board.updateView();
		    }
		});

		board.setCurrentPlayer(board.getTopPlayer());
	}
	@Override
	public void updateView(CardGroup group)
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        //更新JavaFX的主线程的代码放在此处
		    	Game.getInstance().getBoard().getController().showCardGroup(group,
						Game.getInstance().getBoard().getController().getLeftCardGroup());
		    }
		});
	}
}
