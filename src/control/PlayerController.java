package control;

import model.Main;
import model.ConnectFour;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class PlayerController {
	private Main main;
	private ConnectFour connectFour;
	
	@FXML
	private TextField player1;
	@FXML
	private TextField player2;
	
	@FXML
	private Circle circle;
	
	@FXML
	private AnchorPane anchor;
	
	
	/**
	 * Resets the board, empties the list of current players,
	 * adds new players to the game
	 * @param e, ActionEvent button clicked
	 */
	@FXML
	public void playButtonClicked(ActionEvent e) {
		
		connectFour.clear();
		connectFour.clearCurrentPlayers();
		
		connectFour.addPlayerToGame(player1.getText());
		connectFour.addPlayerToGame(player2.getText());
		main.showConnectFour();

	}
	
	@FXML
	public void backButtonClicked(ActionEvent e) {
		main.showMain();
	}
	
	public void setMain(Main main) {
		this.main = main;
		
	}
	
	public void setGame(ConnectFour connectFour) {
		this.connectFour = connectFour;
	}
	
}
