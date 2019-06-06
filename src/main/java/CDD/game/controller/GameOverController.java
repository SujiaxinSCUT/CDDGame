package CDD.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.Tools.FileUtils;
import CDD.game.model.Player.User;
import CDD.game.view.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class GameOverController implements Initializable{

	@FXML
	private Text score;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showScore();
		saveScore();
	}

	public void handleMenu() throws IOException
	{
		App.setRoot("StartView");
	}
	
	public void showScore()
	{
		score.setText(String.valueOf(Game.getInstance().getBoard().getScore()));
	}
	
	public void saveScore()
	{
		if(!Game.getInstance().getUserPlayer().getUser().getName().equals("游客"))
		{
			List<User> users=FileUtils.readUser();
			for(User user:users)
			{
				if(user.getName().equals(Game.getInstance().getUserPlayer().getUser().getName()))
				{
					if(user.getScore()<Game.getInstance().getBoard().getScore())
					user.setScore(Game.getInstance().getBoard().getScore());
					System.out.println(user.getScore());
				}
			}
			try {
				FileUtils.writeUser(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void handleReStart() throws IOException
	{
		Game.getInstance().initBoard();
		App.setRoot("BoardView");
	}
}
