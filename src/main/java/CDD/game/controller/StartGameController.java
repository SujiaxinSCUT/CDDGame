package CDD.game.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.Tools.FileUtils;
import CDD.game.model.Player.User;
import CDD.game.model.Player.UserPlayer;
import CDD.game.view.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class StartGameController implements Initializable{

	@FXML
	private Label StartButton;
	@FXML
	private Button settings;
	@FXML
	private StackPane settingPage;
	@FXML
	private ChoiceBox<User> name;
	@FXML
	private TextField nameTextField;

	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		settingPage.setVisible(false);
		StartButtonEvent();
		SettingEvent();
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
				User user=null;
				if(name.getSelectionModel().getSelectedItem() == null)
				{
					user=new User("游客");
				}else
				{
					user=name.getSelectionModel().getSelectedItem();
				}
				Game.getInstance().setUserPlayer(new UserPlayer(user));
				Game.getInstance().initBoard();
				App.setRoot("BoardView");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public void SettingEvent()
	{
		settings.setOnMouseEntered(e->{
			settings.setPrefHeight(105);
			settings.setPrefWidth(205);
		});
		settings.setOnMouseExited(e->{
			settings.setPrefHeight(100);
			settings.setPrefWidth(200);
		});
		settings.setOnMouseClicked(e->{
			settingPage.setVisible(true);
			List<User>  lists=FileUtils.readUser();
			System.out.println(lists.size());
			if(lists.size()!=0)
			{
				name.getItems().clear();
				name.getItems().addAll(lists);
			}
		});
	}
	
	public void confirm()
	{
		settingPage.setVisible(false);
	}
	
	public void register()
	{
		if(nameTextField.getText()!=null&&!nameTextField.getText().equals(""))
		{
			
			List<User> users=FileUtils.readUser();
			for(User user:users)
			{
				if(user.getName().equals(nameTextField.getText()))
				{
					nameTextField.setText("");
					return;
				}
			}
			User user=new User(nameTextField.getText());
			users.add(user);
			try {
				FileUtils.writeUser(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nameTextField.setText("");
			name.getItems().clear();
			name.getItems().addAll(users);
		}
	}
	
	
}
