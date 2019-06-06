package CDD.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.Card.Filter;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.CardGroup.CardGroupFactory;
import CDD.game.model.Player.UserPlayer;
import CDD.game.view.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardController implements Initializable{

	@FXML
	private HBox playerCards;	
	@FXML
	private Text LeftCardCount;
	@FXML
	private Text RightCardCount;
	@FXML
	private Text TopCardCount;
	@FXML
	private Text mes;
	@FXML
	private Label showCards;
	@FXML
	private Button Pass;
	@FXML
	private HBox cardGroup;
	@FXML
	private Circle leftAlarm;
	@FXML
	private Circle topAlarm;
	@FXML
	private Circle rightAlarm;
	@FXML
	private Text name;
	@FXML
	private HBox topCardGroup;
	@FXML
	private HBox leftCardGroup;
	@FXML
	private HBox rightCardGroup;
	@FXML
	private HBox bottomCardGroup;
	
	
	private Board board;
	
	private List<Card> selectedCard;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		selectedCard=new ArrayList<>();
		board=Game.getInstance().getBoard();
		name.setText(((UserPlayer) Game.getInstance().getUserPlayer()).getUser().getName());
		board.setController(this);
		showCardsEvent();
		PassEvent();

		playerCards.setSpacing(-100);
	    cardGroup.setSpacing(-100);
	    updateView();
        board.RobotRun();
	}
	
	public void closeAlarm() {
		this.leftAlarm.setVisible(false);
		this.topAlarm.setVisible(false);
		this.rightAlarm.setVisible(false);
	}
	
	public void showAlarm()
	{
		if(board.getCurrentPlayer()==board.getLeftPlayer())
		{
			this.leftAlarm.setVisible(true);
		}
		if(board.getCurrentPlayer()==board.getTopPlayer())
		{
			this.topAlarm.setVisible(true);
		}
		if(board.getCurrentPlayer()==board.getRightPlayer())
		{
			this.rightAlarm.setVisible(true);
		}
	}
	
	
	
	
//	出牌按钮事件监听
	public void showCardsEvent() {
		showCards.setOnMouseEntered(e->{
			showCards.setPrefHeight(65);
			showCards.setPrefWidth(130);
		}
		);
		showCards.setOnMouseExited(e->{
			showCards.setPrefHeight(60);
			showCards.setPrefWidth(120);
		});
		showCards.setOnMouseClicked(e->{
		    CardGroup group=CardGroupFactory.create(selectedCard,board.getBottomPlayer());
			if(group==null)
			{
				mes.setText("不符合规则");
			}
			else
			{
				      if(judgeShowCard(group))
				      {
					    UserPlayer player=(UserPlayer) board.getBottomPlayer();
						showCardGroup(group, bottomCardGroup);
						player.showCards(board, group);
						Game.getInstance().showcardMusic();
						selectedCard.clear();				
						updateView();
				      }
				  
				
				
			}
		});
		
	}
	
	public boolean judgeShowCard(CardGroup group)
	{
		if(Filter.DiamondThreejudge(board.getBottomPlayer().getHandCards()))
		{
			if(!Filter.DiamondThreejudge(group.getCards()))
			{
				mes.setText("不包含方块三");
				return false;
			}
		}
		if(board.getCurrentGroups()!=null)
		{
			if(board.getCurrentGroups().getOwner()!=board.getBottomPlayer())
			{
				if(!group.CompareTo(board.getCurrentGroups()))
				{
					updateView();
					mes.setText("不符合规则");
					return false;
				}
			}
		}
		return true;
	}
	
	
//	不出按钮事件监听
	public void PassEvent() {
		Pass.setOnMouseEntered(e->{
			Pass.setPrefHeight(55);
			Pass.setPrefWidth(120);
		}
		);
		Pass.setOnMouseExited(e->{
			Pass.setPrefHeight(50);
			Pass.setPrefWidth(115);
		});
		Pass.setOnMouseClicked(e->{
			UserPlayer player=(UserPlayer) board.getBottomPlayer();
			player.showCards(board, null);
			showCardGroup(null, bottomCardGroup);
			selectedCard.clear();
			updateView();
		});
	}
//	更新视图
	public void updateView() {
		mes.setText("");
		if(board.getCurrentPlayer()==board.getBottomPlayer())
		{
			showCards.setVisible(true);
			if(board.getCurrentGroups()==null||board.getCurrentGroups().getOwner()==board.getBottomPlayer())
			{
				Pass.setVisible(false);
			}
			else
			Pass.setVisible(true);
		}
		else {
			showCards.setVisible(false);
			Pass.setVisible(false);
		}
		playerCards.getChildren().clear();
		List<Card> cards=board.getBottomPlayer().getHandCards();
		for(Card card:cards)
		{
			card.getView().setOnMouseClicked(e->{
				if(!card.getView().isSelected())
				{
				   card.getView().setTranslateY(-40);
				   card.getView().setSelected(true);
				   selectedCard.add(card);
				}else
				{
					card.getView().setTranslateY(0);
					card.getView().setSelected(false);
					selectedCard.remove(card);
				}
			});
			playerCards.getChildren().add(card.getView());
		}
		TopCardCount.setText(String.valueOf(board.getTopPlayer().getHandCards().size()));
		LeftCardCount.setText(String.valueOf(board.getLeftPlayer().getHandCards().size()));
		RightCardCount.setText(String.valueOf(board.getRightPlayer().getHandCards().size()));
		
		closeAlarm();
		showAlarm();
	}
	
	public void gameOver() throws Exception
	{
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    //更新JavaFX的主线程的代码放在此处
		    	showCards.setVisible(false);
		    	Pass.setVisible(false);
		    	cardGroup.getChildren().clear();
				try {
					cardGroup.getChildren().add(App.loadFXML("GameOver"));
					cardGroup.toFront();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}
	
	public void exitGame()
	{
		board.stopRobot();
		try {
			App.setRoot("StartView");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showCardGroup(CardGroup group,HBox side)
	{
		side.getChildren().clear();
		if(group==null)
		{
			Text text=new Text("不出");
			text.setFill(Color.RED);  
			text.setFont(Font.font(30));
			side.getChildren().add(text);
		}
		else
		{
//			side.setAlignment(Pos.CENTER);
//			side.setSpacing(-100);
//			for(Card card:group.getCards())
//			{
//				side.getChildren().add(card.getView());
//			}
			side.getChildren().add(group.getView());
		}
	}

	public HBox getTopCardGroup() {
		return topCardGroup;
	}

	public void setTopCardGroup(HBox topCardGroup) {
		this.topCardGroup = topCardGroup;
	}

	public HBox getLeftCardGroup() {
		return leftCardGroup;
	}

	public void setLeftCardGroup(HBox leftCardGroup) {
		this.leftCardGroup = leftCardGroup;
	}

	public HBox getRightCardGroup() {
		return rightCardGroup;
	}

	public void setRightCardGroup(HBox rightCardGroup) {
		this.rightCardGroup = rightCardGroup;
	}

	public HBox getBottomCardGroup() {
		return bottomCardGroup;
	}

	public void setBottomCardGroup(HBox bottomCardGroup) {
		this.bottomCardGroup = bottomCardGroup;
	}
	
	

}
