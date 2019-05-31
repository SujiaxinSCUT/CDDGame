package CDD.game.controller;

import CDD.game.model.Card.Card;
import CDD.game.model.CardGroup.CardGroup;
import CDD.game.view.CardGroupView;

public class CardGroupController {

	private CardGroup group;
	private CardGroupView view;
	public CardGroupController(CardGroup group, CardGroupView view) {
		super();
		this.group = group;
		this.view = view;
	}
	
	
	
	public CardGroup getGroup() {
		return group;
	}



	public void setGroup(CardGroup group) {
		this.group = group;
	}



	public CardGroupView getView() {
		return view;
	}



	public void setView(CardGroupView view) {
		this.view = view;
	}



	public void updateView()
	{
		view.removeAll();
		for(Card card:group.getCards())
		{
			view.addChildren(card.getView());
		}
	

	}
}
