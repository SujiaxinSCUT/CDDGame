package CDD.game.controller;


import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.Tools.FileUtils;
import CDD.game.model.Card.Card;
import CDD.game.model.Player.User;
import CDD.game.model.Player.UserPlayer;
import CDD.game.view.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class StartGameController implements Initializable{

	@FXML
	private Label StartButton;
	@FXML
	private Label close;
	@FXML
	private Button settings;
	@FXML
	private Button rank;
	@FXML
	private StackPane settingPage;
	@FXML
    private StackPane rankPage;
	@FXML
	private ChoiceBox<User> name;
	@FXML
	private TextField nameTextField;
	
	@FXML
	private Slider SVolumn1;
	@FXML
	private Slider SVolumn2;
	
	@FXML
	private TableView<User> ranktable;
	@FXML
	private TableColumn<User, String> nameColumn;
	@FXML
	private TableColumn<User, Integer> scoreColumn;
	
	private ObservableList<User> UserData = FXCollections.observableArrayList();
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Bind();
		StartButtonEvent();
		SettingEvent();
		RankEvent();
		closeEvent();
		initTable();
	}
	
	public void RankEvent()
	{
		rank.setOnMouseEntered(e->{
			rank.setPrefHeight(75);
			rank.setPrefWidth(155);
		});
		rank.setOnMouseExited(e->{
			rank.setPrefHeight(70);
			rank.setPrefWidth(150);
		});
		rank.setOnMouseClicked(e->{
			settingPage.setVisible(false);
			settingPage.setDisable(true);
			rankPage.setVisible(true);
			rankPage.setDisable(false);
			rankPage.toFront();
			List<User>  lists=FileUtils.readUser();
			Collections.sort(lists, new Comparator<User>() {

				@Override
				public int compare(User u1,User u2) {
					// TODO Auto-generated method stub
					if(u1.getScore()<u2.getScore()) {
						return 1;
					}
					else return -1;
				}

				
				
			});
			UserData.clear();
			UserData.addAll(lists);
		});
	}
	
	public void StartButtonEvent()
	{
		StartButton.setOnMouseEntered(e->{
			StartButton.setPrefHeight(75);
			StartButton.setPrefWidth(155);
		});
		StartButton.setOnMouseExited(e->{
			StartButton.setPrefHeight(70);
			StartButton.setPrefWidth(150);
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
	
	public void closeEvent()
	{
		close.setOnMouseEntered(e->{
			close.setPrefHeight(35);
			close.setPrefWidth(35);
		});
		close.setOnMouseExited(e->{
			close.setPrefHeight(30);
			close.setPrefWidth(30);
		});
		close.setOnMouseClicked(e->{
			rankPage.setVisible(false);
		});
	}
	
	public void SettingEvent()
	{
		settings.setOnMouseEntered(e->{
			settings.setPrefHeight(75);
			settings.setPrefWidth(155);
		});
		settings.setOnMouseExited(e->{
			settings.setPrefHeight(70);
			settings.setPrefWidth(150);
		});
		settings.setOnMouseClicked(e->{
			rankPage.setVisible(false);
			rankPage.setDisable(true);
			settingPage.setVisible(true);
			settingPage.setDisable(false);
			
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
	
	public void Bind() {
		Game.getInstance().getPlayer().getPlayer().
		volumeProperty().bind(SVolumn1.valueProperty().divide(100));
		Game.getInstance().getShowcard().getPlayer().
		volumeProperty().bind(SVolumn2.valueProperty().divide(100));
		SVolumn1.setValue(50);
		SVolumn2.setValue(50);
	}
	
	public void initTable()
	{
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		this.ranktable.setItems(UserData);
	}
}
