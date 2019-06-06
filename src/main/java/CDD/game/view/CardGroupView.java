package CDD.game.view;

import java.util.List;
import java.util.Set;

import CDD.game.model.Card.Card;
import CDD.game.model.CardGroup.CardGroup;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class CardGroupView extends HBox {

	
	
	public CardGroupView(CardGroup group)
	{
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(-65);
		for(Card card:group.getCards())
		{
			this.getChildren().add(card.getView());
		}
	}

	public void removeAll() {
		// TODO Auto-generated method stub
		this.removeAll();
	}

	public void addChildren(CardView view) {
		// TODO Auto-generated method stub
		this.addChildren(view);
		System.out.println("add");
	}
	
	
	
}
