package CDD.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.model.Player.UserPlayer;
import CDD.game.view.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StartGameController implements Initializable{

	@FXML
	private Label StartButton;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StartButtonEvent();
	}
	
	public void StartButtonEvent()
	{
		StartButton.setOnMouseEntered(e->{
			StartButton.setPrefHeight(105);
			StartButton.setPrefWidth(205);
		});
		StartButton.setOnMouseExited(e->{
			StartButton.setPrefHeight(100);
			StartButton.setPrefWidth(200);
		});
		StartButton.setOnMouseClicked(e->{
			try {
				Game.getInstance().setUserPlayer(new UserPlayer());
				Game.getInstance().initBoard();
				App.setRoot("BoardView");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
