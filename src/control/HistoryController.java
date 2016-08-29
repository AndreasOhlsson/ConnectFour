package control;

import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import model.ConnectFour;
import model.Main;

public class HistoryController {
	
	private Main main;
	private ConnectFour connectFour;
	
	@FXML
	private ScrollPane historyPane;
	
	@FXML
	private Label historyLabel;
	
	@FXML
	public void showHistory() {
		
		historyLabel.setText("");
		historyLabel.setText(connectFour.getAuditlog());
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
