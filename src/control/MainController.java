package control;
import model.Main;
import model.ConnectFour;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class MainController {
	private Main main;
	private ConnectFour connectFour;
	
	@FXML
	public void gameButtonClicked(ActionEvent e) {
		main.showPlayerPane();
	}
	
	@FXML
	public void highscoreButtonClicked(ActionEvent e) {
		main.showHighscore();
	}
	
	@FXML
	public void historyButtonClicked(ActionEvent e) {
		main.showHistory();
	}
	
	
	@FXML
	public void quitButtonClicked(ActionEvent e) {
		connectFour.saveAll();
		Platform.exit();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setGame(ConnectFour connectFour) {
		this.connectFour = connectFour;
	}
	

}
