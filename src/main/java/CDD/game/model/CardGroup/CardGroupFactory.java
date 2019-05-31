package CDD.game.model.CardGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import CDD.game.model.Card.Card;
import CDD.game.model.Card.CardFactory;
import CDD.game.model.Player.Player;

public class CardGroupFactory {

	public static CardGroup create(List<Card> totalcard,Player owner) {
		List<Card> cards=new ArrayList<>();
		for(Card card:totalcard)
		{
			cards.add(card);
		}
		CardGroup group=null;
		switch(cards.size())
		{
		case 1:{
			group=new CardGroup();
			group.setCards(cards);
			group.setCardType(CardType.Single);
			group.setOwner(owner);
			break;
		}
		case 2:{
			if(isEqual(cards))
			{
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.Pairs);
				group.setOwner(owner);
			}
		}break;
		case 3:{
			if(isEqual(cards))
			{
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.Third);
				group.setOwner(owner);
			}
		}break;
		case 5:{
			if(isSimple(cards)) {
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.Simple);
				group.setOwner(owner);
				break;
			}
			if(isSameColor(cards))
			{
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.SameColor);
				group.setOwner(owner);
				break;
			}
			if(isThreeBeltTwo(cards)) {
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.ThreeBeltTwo);
				group.setOwner(owner);
				break;
			}
			if(isFourBeltOne(cards)) {
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.FourBeltOne);
				group.setOwner(owner);
				break;
			}
			if(isStraightFlush(cards)) {
				group=new CardGroup();
				group.setCards(cards);
				group.setCardType(CardType.StraightFlush);
				group.setOwner(owner);
				break;
			}
				
		}
		}
		
		return group;
	}
	
	public static boolean isEqual(List<Card> cards){
		int point =cards.get(0).getPoint();
		for(Card card:cards)
		{
			if(card.getPoint()!=point)
				return false;
		}
		return true;
	}
	
	public static boolean isSimple(List<Card> cards)
	{
		CardFactory.sortByPoint(cards);
		int point=cards.get(0).getPoint();
		if(point==13)
			return false;
		for(int i=1;i<5;i++)
		{
			if(cards.get(i).getPoint()-point!=-1&&cards.get(i).getPoint()-point!=1)
			{
				return false;
			}else
			{
				point=cards.get(i).getPoint();
			}
		}
		return true;
	}
	
	public static boolean isThreeBeltTwo(List<Card> cards)
	{
		CardFactory.sortByPoint(cards);
		List<Card> temp=cards;
		List<Card> TopThree=new ArrayList<>();
		for(int i=0;i<3;i++)
		{
			TopThree.add(temp.get(i));
		}
		List<Card> BackThree=new ArrayList<>();
		for(int i=3;i<5;i++)
		{
			BackThree.add(temp.get(i));
		}
		if(isEqual(TopThree))
		{
			temp.removeAll(TopThree);
			if(isEqual(temp))
			{
				return true;
			}
		}else if(isEqual(BackThree))
		{
			temp.removeAll(BackThree);
			if(isEqual(temp))
				return true;
		}
		return false;
	}
	
	public static boolean isFourBeltOne(List<Card> cards)
	{
		CardFactory.sortByPoint(cards);
		List<Card> temp=new ArrayList<>();
		for(Card card:cards)
		{
			temp.add(card);
		}
		List<Card> TopFour=new ArrayList<>();
		for(int i=0;i<4;i++)
		{
			TopFour.add(temp.get(i));
		}
		if(isEqual(TopFour)) {
			return true;
		}else
		{
			temp.remove(0);
			if(isEqual(temp))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSameColor(List<Card> cards)
	{
		CardFactory.sortByPoint(cards);
		int color=cards.get(0).getColor();
		for(Card card:cards)
		{
			if(card.getColor()!=color)
				return false;
		}
		return true;
	}
	
	public static boolean isStraightFlush(List<Card> cards)
	{
		return isSameColor(cards)&&isSimple(cards);
	}
	
}
