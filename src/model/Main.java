package model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

import control.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	private Stage primaryStage;
	private ConnectFour connectFour = new ConnectFour();
	private BorderPane window;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Connect Four");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ParentPane.fxml"));
			window = (BorderPane) loader.load();
			Scene scene = new Scene(window, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    connectFour.saveAll();
                    Platform.exit();
                }
			});


		} catch (Exception e) {
			e.printStackTrace();
		}
		showMain();
	}

	public void showMain() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();
			window.setCenter(anchorPane);
			MainController controller = loader.getController();
			controller.setMain(this);
			controller.setGame(connectFour);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void showPlayerPane() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PlayerNames.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			window.setCenter(anchor);
			PlayerController controller = loader.getController();
			controller.setMain(this);
			controller.setGame(connectFour);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showHighscore() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Highscore.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			window.setCenter(anchor);
			HighScoreController controller = loader.getController();
			controller.setMain(this);
			controller.setGame(connectFour);
			controller.showHighScore();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showHistory() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/History.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			window.setCenter(anchor);
			HistoryController controller = loader.getController();
			controller.setMain(this);
			controller.setGame(connectFour);
			controller.showHistory();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void showConnectFour() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Grid.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			window.setCenter(anchor);
			GameController controller = loader.getController();
			controller.setMain(this);
			controller.setGame(connectFour);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void showDialog(String name) {
		Stage dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		Scene scene = new Scene(new Group(new Text(25, 20, "The winner is: " + name)),300,100);
		dialog.setTitle("WinDialog");
		dialog.setScene(scene);
		dialog.show();
	}

}
