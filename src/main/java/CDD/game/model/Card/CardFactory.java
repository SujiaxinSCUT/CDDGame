package CDD.game.model.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CardFactory {

	private static List<Card> TotalCardGroup;
	
	public static Card create(int cardType,int point)
	{
		Card card=new Card(cardType,point);
		return card;
		
//		if(cardType<=13&&cardType>=1)
//		{
//			card.setColor(Card.Diamond);
//			card.setPoint(cardType);
//			card.creatView();
//			return card;
//		}
//		if(cardType<=26&&cardType>=14)
//		{
//			card.setColor(Card.Club);
//			card.setPoint(cardType-13);
//			card.creatView();
//			return card;
//		}
//		if(cardType<=39&&cardType>=27)
//		{
//			card.setColor(Card.Heart);
//			card.setPoint(cardType-26);
//			card.creatView();
//			return card;
//		}
//		if(cardType<=52&&cardType>=40)
//		{
//			card.setColor(Card.Spade);
//			card.setPoint(cardType-39);
//			card.creatView();
//			return card;
//		}
//		return null;
		
	    
		
	}
	
	public static void shuffle()
	{
		if(TotalCardGroup==null)
			TotalCardGroup=new ArrayList<Card>();			
		if(TotalCardGroup.size()!=0)
			return;
		for(int i=1;i<=4;i++)
		{
			for(int j=1;j<=13;j++)
			{
				TotalCardGroup.add(CardFactory.create(i, j));
			}
		}
	}
	
	public static List<Card> getCards()
	{
		List<Card> cards=new ArrayList<>();
		if(TotalCardGroup.size()==13)
		{
			cards.addAll(TotalCardGroup);
			TotalCardGroup.removeAll(cards);
			cards=sortByPoint(cards);
			return cards;
		}
		for(int i=0;i<13;i++)
		{
			int rand=(int) (Math.random()*TotalCardGroup.size());
			Card card=TotalCardGroup.get(rand);
			cards.add(card);
            TotalCardGroup.remove(card);			
		}
		cards=sortByPoint(cards);
		return cards;	
	}
	
	public static List<Card> sortByPoint(List<Card> cards)
	{
		Collections.sort(cards, new Comparator<Card>() {

			@Override
			public int compare(Card o1, Card o2) {
				// TODO Auto-generated method stub
				if(o1.getPoint()<o2.getPoint()) {
					return 1;
				}
				else return -1;
			}

			
			
		});
		return cards;
	}
	
	public static List<Card> sortByColor(List<Card> cards)
	{
		Collections.sort(cards, new Comparator<Card>() {

			@Override
			public int compare(Card o1, Card o2) {
				// TODO Auto-generated method stub
				if(o1.getColor()<o2.getColor()) {
					return 1;
				}
				else return -1;
			}

			
			
		});
		return cards;
	}
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
