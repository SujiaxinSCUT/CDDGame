package CDD.game.model.Card;

import CDD.game.view.CardView;

public class Card {

	
	private int point;
	
	private int color;
	
	public final static int Spade=4;
	
	public final static int Heart=3;
	
	public final static int Club=2;
	
	public final static int Diamond=1;
	
    private CardView view;
	
	
	public Card(int color,int point)
	{
		this.color=color;
		this.point=point;
		this.view=new CardView(this);
	}

    public Card() {
    	
    }


	public int getPoint() {
		return point;
	}




	public void setPoint(int point) {
		this.point = point;
	}




	public int getColor() {
		return color;
	}




	public void setColor(int color) {
		this.color = color;
	}
	
	public void creatView()
	{
		this.view=new CardView(this);
	}

	public CardView getView() {
		return view;
	}

	
	
}

