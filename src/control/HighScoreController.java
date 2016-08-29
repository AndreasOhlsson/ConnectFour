package control;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import model.ConnectFour;
import model.Main;

public class HighScoreController {
	
	private Main main;
	private ConnectFour connectFour;
	
	@FXML
	private ScrollPane highscorePane;
	
	@FXML
	private Label highscoreLabel;
	
	@FXML
	public void showHighScore() {
		highscoreLabel.setText("");
		highscoreLabel.setText(connectFour.showHighscore());
	}
	
	
	@FXML
	public void backButtonClicked() {
		main.showMain();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setGame(ConnectFour connectFour) {
		this.connectFour = connectFour;
	}

}
