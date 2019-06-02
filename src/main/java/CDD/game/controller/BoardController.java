package CDD.game.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import CDD.game.Game;
import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.Card.DiamondThreeFilter;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.CardGroup.CardGroupFactory;
import CDD.game.model.Player.UserPlayer;
import CDD.game.model.Round.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
	
	private Board board;
	
	private List<Card> selectedCard;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		selectedCard=new ArrayList<>();
		board=Game.getInstance().getBoard();
		board.setController(this);
		showCardsEvent();
		PassEvent();
		
		playerCards.setSpacing(-100);
	    cardGroup.setSpacing(-100);
	    updateView();
	    board.showCards();
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
				mes.setText("不符合规则");
			else
			{
				System.out.println(group.getCards().size());
				if(DiamondThreeFilter.judge(board.getBottomPlayer().getHandCards()))
				{
					if(!DiamondThreeFilter.judge(group.getCards()))
					{
						mes.setText("不包含方块三");
						return;
					}
				}
				board.getBottomPlayer().showCards(group.getCards());
				board.setCurrentGroups(group);
				board.showCards();
				selectedCard.clear();
				
				updateView();
			}
		});
		
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
			board.showCards();
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
		CardGroup group=board.getCurrentGroups();
		if(group!=null)
		{
			this.cardGroup.getChildren().clear();
			for(Card card:group.getCards())
			{
				
				cardGroup.getChildren().add(card.getView());
			}
		}
		
	}

}
