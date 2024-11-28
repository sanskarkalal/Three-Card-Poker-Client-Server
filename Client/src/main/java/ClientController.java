import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ClientController {

    public VBox welcomeScreen;
    public VBox gameScreen;
    @FXML
    private TextField anteBet,pairBet;
    @FXML
    private AnchorPane root;

    @FXML
    private Label welcomeText;
    @FXML
    private Label promptText;
    @FXML
    private Label gamePromptText;
    @FXML
    private TextField ipText;

    @FXML
    private TextField portText;

    @FXML
    private Button startButton;

    Client clientconnection;
    PokerInfo pokerinfo = new PokerInfo();
    Stage stage;
    Scene scene;

    public void initStageScene(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }



    @FXML
    private void switchToGameScreen() throws IOException {
        welcomeScreen.setVisible(false); // Replace the content of the existing root
        gameScreen.setVisible(true);
    }

    private void updateInfo(PokerInfo data) {

        this.pokerinfo = data;

    }


    public void play(ActionEvent actionEvent) {
        try {
            switchToGameScreen();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String ip = ipText.getText();
        String port = portText.getText();
        System.out.println("IP: " + ip + ", Port: " + port);

        clientconnection = new Client(data -> {
            Platform.runLater(() -> {
                updateInfo(data);
            });
        });

        clientconnection.start();

    }

    public void sendBets(ActionEvent actionEvent) {

        pokerinfo.player.setAnteBet(parseInt(anteBet.getText()));
        pokerinfo.player.setPairPlusBet(parseInt(pairBet.getText()));
        clientconnection.send(pokerinfo);

        for(Card c: pokerinfo.player.getHand()){
            System.out.println(c.getSuit() + " " + c.getValue());
        }
        for(Card c: pokerinfo.dealer.getDealersHand()){
            System.out.println(c.getSuit() + " " + c.getValue());
        }

    }
}