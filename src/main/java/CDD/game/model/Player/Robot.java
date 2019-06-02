package CDD.game.model.Player;

import java.util.ArrayList;
import java.util.List;

import CDD.game.model.Card.Card;
import CDD.game.model.Card.DiamondThreeFilter;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.CardGroup.CardGroupFactory;

public class Robot extends Player{

	public CardGroup showCards(){
		// TODO Auto-generated method stub
		if(DiamondThreeFilter.judge(this.getHandCards()))
		{
			Card DiamondThree=null;
			for(Card card:this.getHandCards())
			{
				if(card.getPoint()==1&&card.getColor()==Card.Diamond)
				{
					DiamondThree=card;
				}
			}
			List<Card> cards=new ArrayList<>();
			cards.add(DiamondThree);
			this.getHandCards().remove(DiamondThree);
			return CardGroupFactory.create(cards,this);
		}
		
		
		return null;
	}

	
	

}
