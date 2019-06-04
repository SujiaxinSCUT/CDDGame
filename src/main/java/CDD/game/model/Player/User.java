package CDD.game.model.Player;

import java.io.Serializable;

public class User implements Serializable{

	private String name;
	private int score;
	
	
	
	public User(String name) {
		super();
		this.name = name;
		this.score=0;
	}
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", score=" + score + "]";
	}
	
	
}
