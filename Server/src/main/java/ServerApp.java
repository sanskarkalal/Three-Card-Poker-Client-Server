import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.function.Consumer;

public class ServerApp extends Application {

	private Scene mainScene, logScene;
	private ListView<String> logListView;
	private Server serverConnection;

	@Override
	public void start(Stage primaryStage) {
		// Initialize the main scene (port input)
		mainScene = createMainScene(primaryStage);

		// Initialize the log scene (server log view)
		logScene = createLogScene();

		// Set the main scene initially
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Server Application");
		primaryStage.show();
	}

	private Scene createMainScene(Stage stage) {
		// Input field for port
		TextField portField = new TextField();
		portField.setPromptText("Enter port number");

		// Start button
		Button startButton = new Button("Start Server");
		startButton.setOnAction(e -> {
			String portText = portField.getText();
			if (portText.isEmpty()) {
				System.out.println("Port number is required.");
				return;
			}
			try {
				int port = Integer.parseInt(portText);

				// Start the server
				serverConnection = new Server(data -> {
					Platform.runLater(() -> logListView.getItems().add(data.toString()));
				});

				System.out.println("Server started on port: " + port);

				// Switch to the log scene
				stage.setScene(logScene);

			} catch (NumberFormatException ex) {
				System.out.println("Invalid port number. Please enter a valid integer.");
			}
		});

		// Layout for the main scene
		VBox layout = new VBox(10, portField, startButton);
		layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
		return new Scene(layout, 400, 200);
	}

	private Scene createLogScene() {
		// ListView to display server logs
		logListView = new ListView<>();

		// Stop button
		Button stopButton = new Button("Stop Server");
		stopButton.setOnAction(e -> {
			if (serverConnection != null) {
				System.out.println("Stopping server...");
				serverConnection = null;
				System.exit(0);// Simulate stopping the server
			}
		});

		// Layout for the log scene
		VBox layout = new VBox(10, logListView, stopButton);
		layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
		return new Scene(layout, 600, 400);
	}

	public static void main(String[] args) {
		launch(args);
	}
}