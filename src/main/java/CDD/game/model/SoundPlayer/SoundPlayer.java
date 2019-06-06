package CDD.game.model.SoundPlayer;

import CDD.game.view.App;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundPlayer implements Runnable{

	private Media media;
	private MediaPlayer player;
	
	
	public SoundPlayer()
	{
		media=new Media(App.class.getResource("music/target.mp3").toString());
		player=new MediaPlayer(media);
	}
	
	public MediaPlayer getPlayer() {
		return player;
	}




	public void setPlayer(MediaPlayer player) {
		this.player = player;
	}




	@Override
	public void run()
	{
		player.setAutoPlay(true);
	}
}
