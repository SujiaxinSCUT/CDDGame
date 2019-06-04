package CDD.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.view.App;
import javafx.fxml.Initializable;

public class GameOverController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void handleMenu() throws IOException
	{
		App.setRoot("StartView");
	}
	
	public void handleReStart() throws IOException
	{
		Game.getInstance().initBoard();
		App.setRoot("BoardView");
	}
}
