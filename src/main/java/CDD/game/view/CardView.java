package CDD.game.view;

import CDD.game.model.Card.Card;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CardView extends StackPane {

	
    private Label color;
    private Text point;
    private boolean selected=false;
    
    
    
    
    public CardView(Card card)
    {
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	this.setAlignment(Pos.TOP_LEFT);
    	this.setPrefHeight(140);
    	this.setPrefWidth(100);
    	this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    	this.getStyleClass().add("Card");
    	
    	color=new Label();
    	point =new Text();
    	
    	StackPane.setMargin(color, new Insets(30,0,0,10));
    	StackPane.setMargin(point, new Insets(0,0,0,10));
    	
    	point.setFont(Font.font("System", FontWeight.BOLD	, 24));
    	color.setPrefWidth(20);
    	color.setPrefHeight(20);
        
    	switch(card.getColor())
    	{
    	case Card.Club:color.getStyleClass().add("club");;point.setFill(Color.BLACK);break;
    	case Card.Diamond:color.getStyleClass().add("diamond");point.setFill(Color.RED);break;
    	case Card.Heart:color.getStyleClass().add("heart");point.setFill(Color.RED);break;
    	case Card.Spade:color.getStyleClass().add("spade");point.setFill(Color.BLACK);break;
    	}
    	if(card.getPoint()<=8)
    	point.setText(String.valueOf(card.getPoint()+2));
    	else
    	{
    		switch(card.getPoint())
    		{
    		case 9:point.setText("J");break;
    		case 10:point.setText("Q");break;
    		case 11:point.setText("K");break;
    		case 12:point .setText("A");break;
    		case 13:point.setText("2");break;
    		}
    	}
    	
    	this.getChildren().addAll(color,point);
    }




	public boolean isSelected() {
		return selected;
	}




	public void setSelected(boolean selected) {
		this.selected = selected;
	}
    
    
}
