package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import model.ConnectFour;
import model.Main;

public class GameController {

	private Main main;
	private ConnectFour connectFour;
	private int[][] grid;
	private int turn = 0;

	@FXML
	private GridPane gridPane;
	@FXML
	private Label turn1Label;
	@FXML
	private Label turn2Label;
	@FXML
	private Circle circle;

	@FXML
	public void mainMenuButtonClicked(ActionEvent e) {
		main.showMain();
	}

	@FXML
	public void newGameButtonClicked(ActionEvent e) {
		connectFour.clear();
		main.showPlayerPane();
	}

	/**
	 * Checks which button was pressed and tries to put a circle in the specified column
	 * @param e, ActionEvent from button click
	 */
	@FXML
	public void gridButtonClicked(ActionEvent e) {
		String id = ((Button) e.getSource()).getId();
		String digit = id.replaceAll("[^0-9.]", "");
		int status = connectFour.putCircle(Integer.valueOf(digit), 0);
		if (status != -1) {
			paintCircle();
			switchTurnLabel();
			if (status == 1) {
				main.showDialog(connectFour.getPlayerWinnerName());
			}
		}
		
	}
	
	/**
	 * Switches the active labels depending on whose turn it is
	 */
	private void switchTurnLabel() {
		if (!connectFour.isFinished()) {
			if (turn == 0) {
				turn1Label.setVisible(false);
				turn2Label.setVisible(true);
				turn = 1;
			} else if (turn == 1) {
				turn1Label.setVisible(true);
				turn2Label.setVisible(false);
				turn = 0;
			}
		}

	}

	/**
	 * Paints all the grids to match the matrix, blue for player1 and green for player2
	 */
	private void paintCircle() {
		grid = connectFour.getMatrix();
		for (int i = 0; i < connectFour.getHeight(); i++) {
			for (int j = 0; j < connectFour.getWidth(); j++) {
				if (grid[j][i] == 0) {
					gridPane.add(new Circle(gridPane.getColumnConstraints().get(0).getPrefWidth() / 4,
							Paint.valueOf("blue")), j, i);
				} else if (grid[j][i] == 1) {
					gridPane.add(new Circle(gridPane.getColumnConstraints().get(0).getPrefWidth() / 4,
							Paint.valueOf("green")), j, i);
				} else {

				}
			}
		}

	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setGame(ConnectFour connectFour) {
		this.connectFour = connectFour;
	}

}
