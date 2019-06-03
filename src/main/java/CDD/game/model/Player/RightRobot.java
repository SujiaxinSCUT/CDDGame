package CDD.game.model.Player;

import CDD.game.model.Board.Board;
import javafx.application.Platform;

public class RightRobot extends Robot{

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
		board.setCurrentPlayer(board.getBottomPlayer());
	}
}
