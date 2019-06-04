package CDD.game.model.SoundPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ShowCard extends Thread{

	private Media media;
	private MediaPlayer player;
	
	public ShowCard()
	{
		media=new Media(getClass().getResource("showCard.mp3").toString());
		player=new MediaPlayer(media);
	}
	
	
	
	public MediaPlayer getPlayer() {
		return player;
	}



	public void setPlayer(MediaPlayer player) {
		this.player = player;
	}



	public void run()
	{
		player.play();
        player.seek(Duration.ZERO);
	}
}
