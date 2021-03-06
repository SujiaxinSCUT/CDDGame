package CDD.game.model.Player;

import java.util.ArrayList;
import java.util.List;

import CDD.game.Game;
import CDD.game.model.Board.Board;
import CDD.game.model.Card.Card;
import CDD.game.model.Card.Filter;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.model.CardGroup.CardGroupFactory;
import CDD.game.model.CardGroup.CardType;
import CDD.game.view.App;
import javafx.application.Platform;

public class Robot extends Player implements Runnable{

	private Board board;
	
	public boolean running=true;
	
	public void showCards(){
		// TODO Auto-generated method stub
		
		if(Filter.DiamondThreejudge(this.getHandCards()))
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
			CardGroup group=CardGroupFactory.create(cards,this);
			board.setCurrentGroups(group);
			this.updateView(group);
			this.changeTurn(board);
			Game.getInstance().showcardMusic();
			return;
		}
		
		if(board.getCurrentGroups().getOwner()==this)
		{
		    List<Card> cards=new ArrayList<>();
		    cards.add(this.getHandCards().get(getHandCards().size()-1));
		    this.getHandCards().removeAll(cards);
		    
			CardGroup group=CardGroupFactory.create(cards,this);
			board.setCurrentGroups(group);
			Game.getInstance().showcardMusic();
			this.updateView(group);
			if(getHandCards().size()==0)
			{
				board.setFlag(false);
				return;
			}
			this.changeTurn(board);			
			return;
		}
		
		switch(board.getCurrentGroups().getCardType())
		{
		case CardType.Single:{
			Card card=getSingle(board.getCurrentGroups().getCards().get(0));
			if(card!=null)
			{
				List<Card> cards=new ArrayList<>();
				cards.add(card);
			    this.getHandCards().removeAll(cards);
				CardGroup group=CardGroupFactory.create(cards,this);
				board.setCurrentGroups(group);
				Game.getInstance().showcardMusic();
				this.updateView(group);
				if(getHandCards().size()==0)
				{
					board.setFlag(false);
					return;
				}
				this.changeTurn(board);
				
				return;
			}
		}break;
		case CardType.Pairs:{
			List<Card> cards=getPairsOrThree(board.getCurrentGroups().getCards());
			if(cards!=null)
			{
				this.getHandCards().removeAll(cards);
				CardGroup group=CardGroupFactory.create(cards,this);
				board.setCurrentGroups(group);
				Game.getInstance().showcardMusic();
				this.updateView(group);
				if(getHandCards().size()==0)
				{
					board.setFlag(false);
					return;
				}
				this.changeTurn(board);
				return;
			}
		}break;
		case CardType.Third:{
			List<Card> cards=getPairsOrThree(board.getCurrentGroups().getCards());
			if(cards!=null)
			{
				this.getHandCards().removeAll(cards);
				CardGroup group=CardGroupFactory.create(cards,this);
				board.setCurrentGroups(group);
				Game.getInstance().showcardMusic();
				this.updateView(group);
				if(getHandCards().size()==0)
				{
					board.setFlag(false);
					return;
				}
				this.changeTurn(board);
				
				return;
			}
		}break;
		}
		
		this.updateView(null);
		this.changeTurn(board);
		
	}

	public Card getSingle(Card card)
	{
		for(int i=this.getHandCards().size()-1;i>=0;i--)
		{
			if(getHandCards().get(i).getPoint()>card.getPoint())
			{
				return getHandCards().get(i);
			}
			else if(getHandCards().get(i).getPoint()==card.getPoint())
		    {
				if(getHandCards().get(i).getColor()>card.getColor())
				{
					return getHandCards().get(i);
				}
		    }
		}
		return null;
	}
	
	public List<Card> getPairsOrThree(List<Card> cards)
	{
		Card sample=cards.get(0);
		for(int i=this.getHandCards().size()-1;i>=0;i--)
		{
			if(this.getHandCards().get(i).getPoint()>sample.getPoint())
			{
			List<Card> temp=new ArrayList<>();
			temp.addAll(getHandCards());
			for(Card card:this.getHandCards())
			{
				if(card.getPoint()!=getHandCards().get(i).getPoint())
				{
					temp.remove(card);
				}
			}
			if(temp.size()==cards.size())
			{
				return temp;
			}
			}
		}
		return null;
	}
	
	public void updateView(CardGroup group)
	{
		
	}
	
	public void changeTurn(Board board)
	{
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		board=Game.getInstance().getBoard();
		while(running)
		{
			if(board.getCurrentPlayer()==this)
			{
			
			Platform.runLater(new Runnable() {
			    @Override
			    public void run() {
			    //更新JavaFX的主线程的代码放在此处
				board.updateView();
			    }
			});
			while(board.isStop())
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.showCards();

			
			
			}
		}
	}
	

}
