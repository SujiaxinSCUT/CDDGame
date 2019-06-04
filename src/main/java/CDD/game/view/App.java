package CDD.game.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import CDD.game.Game;
import CDD.game.model.Card.Card;
import CDD.game.model.SoundPlayer.SoundPlayer;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    
    
    @Override
    public void start(Stage stage) throws IOException {

        Game.getInstance().playMusic();
        scene=new Scene(loadFXML("StartView"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml+".fxml"));
        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}