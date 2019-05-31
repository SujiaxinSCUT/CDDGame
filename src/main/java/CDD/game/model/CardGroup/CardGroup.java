package CDD.game.model.CardGroup;

import java.util.List;
import java.util.Set;

import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.Player.Player;

public class CardGroup {

	private int cardType;
	
	private List<Card> cards;

	private Player owner;
	
	public CardGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardGroup(int cardType, List<Card> cards) {
		super();
		this.cardType = cardType;
		this.cards = cards;
	}

	
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public boolean CompareTo(CardGroup group)
	{
		if(this.cards.size()!=group.getCards().size())
			return false;
		switch(this.cards.size())
		{
		case 1:return compareSingle(this, group);
		case 2:return comparePairs(this, group);
		case 3:return compareThree(this, group);
		case 5:return compareFive(this, group);
		}
		return true;
	}
	
	private boolean compareSingle(CardGroup g1,CardGroup g2)
	{
		if(g1.getCards().get(0).getPoint()>g2.getCards().get(0).getPoint())			
		return true;
		else if(g1.getCards().get(0).getPoint()==g2.getCards().get(0).getPoint())
		{
			if(g1.getCards().get(0).getColor()>g2.getCards().get(0).getColor())
				return true;
		}
			
		return false;
	}
	
	private boolean comparePairs(CardGroup g1,CardGroup g2)
	{
		
		if(g1.getCards().get(0).getPoint()>g2.getCards().get(0).getPoint())
		return true;
		else
		{
			if(g1.getCards().get(0).getPoint()==g2.getCards().get(0).getPoint())
			{
				CardFactory.sortByColor(g1.getCards());
				CardFactory.sortByColor(g2.getCards());
				if(g1.getCards().get(0).getColor()>g2.getCards().get(0).getColor())
					return true;
			}
		}
		return false;
	}
	
	private boolean compareThree(CardGroup g1,CardGroup g2)
	{
		if(g1.getCards().get(0).getPoint()>g2.getCards().get(0).getPoint())
			return true;
		return false;
	}
	
	private boolean compareFive(CardGroup g1,CardGroup g2)
	{
		if(g1.getCardType()>g2.getCardType())
			return true;
		else if(g1.getCardType()==g2.getCardType())
		{
			switch(g1.getCardType())
			{
			case CardType.Simple:return compareSimpleAndSameColor(g1.getCards(),g2.getCards());
			case CardType.SameColor:return compareSimpleAndSameColor(g1.getCards(),g2.getCards());
			case CardType.ThreeBeltTwo:return compareThreeBeltTwo(g1.getCards(), g2.getCards());
			case CardType.FourBeltOne:return compareFourBeltOne(g1.getCards(), g2.getCards());
			case CardType.StraightFlush:return compareSimpleAndSameColor(g1.getCards(),g2.getCards());
			}
		}
		return false;
	}
	
	private boolean compareSimpleAndSameColor(List<Card> c1,List<Card> c2)
	{
		if(c1.get(0).getPoint()>c2.get(0).getPoint())
			return true;
		else if(c1.get(0).getPoint()==c2.get(0).getPoint())
		{
			if(c1.get(0).getColor()>c2.get(0).getColor())
				return true;
		}
		return false;
	}
	
	public boolean compareThreeBeltTwo(List<Card> c1,List<Card> c2)
	{
		int point1=c1.get(0).getPoint();
		int count=1;
		for(Card card:c1)
		{
			if(card.getPoint()==point1)
				count++;
		}
		if(count!=3)
		{
			point1=c1.get(4).getPoint();
		}
		
		int point2=c2.get(0).getPoint();
		count=1;
		for(Card card:c2)
		{
			if(card.getPoint()==point2)
				count++;
		}
		if(count!=3)
		{
			point2=c2.get(4).getPoint();
		}
		if(point1>point2)
			return true;
		return false;
	}
	
	private boolean compareFourBeltOne(List<Card> c1,List<Card> c2)
	{
		int point1=c1.get(0).getPoint();
		if(point1!=c1.get(1).getPoint())
		{
			point1=c1.get(1).getPoint();
		}
		
		int point2=c2.get(0).getPoint();
		if(point2!=c2.get(1).getPoint())
		{
			point2=c2.get(1).getPoint();
		}
		
		if(point1>point2)
			return true;
		return false;
	}
	
	
}

