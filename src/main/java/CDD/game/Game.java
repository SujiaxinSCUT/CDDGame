package CDD.game;

import CDD.game.model.Board.Board;
import CDD.game.model.Player.Player;
import CDD.game.model.Player.UserPlayer;
import CDD.game.model.SoundPlayer.ShowCard;
import CDD.game.model.SoundPlayer.SoundPlayer;

public class Game {

	private static class GameInstance{
		private static final Game instance=new Game();
	}
	
	private Game() {player=new SoundPlayer();showcard=new ShowCard();}
	
	public static Game getInstance()
	{
		return GameInstance.instance;
	}
	
	private ShowCard showcard;
	
	private SoundPlayer player;
	
	private UserPlayer userPlayer;
	
	private Board board;
	
	public void playMusic()
	{
		Thread t1=new Thread(player);
		t1.start();
	}
	
	public void showcardMusic() {
		showcard.run();
	}
	
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

	public SoundPlayer getPlayer() {
		return player;
	}

	public void setPlayer(SoundPlayer player) {
		this.player = player;
	}

	public ShowCard getShowcard() {
		return showcard;
	}

	public void setShowcard(ShowCard showcard) {
		this.showcard = showcard;
	}
	
	
}
