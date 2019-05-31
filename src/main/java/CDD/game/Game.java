package CDD.game;

import CDD.game.model.Board.Board;
import CDD.game.model.Player.Player;
import CDD.game.model.Player.UserPlayer;

public class Game {

	private static class GameInstance{
		private static final Game instance=new Game();
	}
	
	private Game() {}
	
	public static Game getInstance()
	{
		return GameInstance.instance;
	}
	
	
	
	private UserPlayer userPlayer;
	
	private Board board;
	
	public void initBoard()
	{
		this.board=new Board(this.userPlayer);
	}

	public Player getUserPlayer() {
		return userPlayer;
	}

	public void setUserPlayer(UserPlayer userPlayer) {
		this.userPlayer = userPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
