package main.tictactoe.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import main.tictactoe.MainApp;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class GameInterfaceController implements Initializable {

    ArrayList<Button> buttons;

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Button start;
    @FXML
    private Text winnerText;
    @FXML
    private RadioButton cpuVscpu;
    @FXML
    private RadioButton humVscpu;
    @FXML
    private RadioButton humVshum;
    private int playerTurn = 0;
    private int overallTurn = 0;
    private MainApp mainApp;
    private ToggleGroup radioGroup = new ToggleGroup();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9));

        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        cpuVscpu.setToggleGroup(radioGroup);
        humVscpu.setToggleGroup(radioGroup);
        humVshum.setToggleGroup(radioGroup);
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText(" ");
    }
    public void resetButton(Button button){
        button.setDisable(false);
        button.setText(" ");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }
    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        } else{
            button.setText("O");
            playerTurn = 0;
        }
        ++overallTurn;
    }
    @FXML
    void cpuGameStyle(ActionEvent actionEvent){

    }
    private Button cpuMoves() {
        int randomPos = ThreadLocalRandom.current().nextInt(0,8);
        Button cpu = buttons.get(randomPos);

    }

    public void checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> b1.getText() + b2.getText() + b3.getText();
                case 1 -> b4.getText() + b5.getText() + b6.getText();
                case 2 -> b7.getText() + b8.getText() + b9.getText();
                case 3 -> b1.getText() + b5.getText() + b9.getText();
                case 4 -> b3.getText() + b5.getText() + b7.getText();
                case 5 -> b1.getText() + b4.getText() + b7.getText();
                case 6 -> b2.getText() + b5.getText() + b8.getText();
                case 7 -> b3.getText() + b6.getText() + b9.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                winnerText.setText("X won!");
                buttons.forEach(this::resetButton);
                overallTurn = 0;
            }

            //O winner
            else if (line.equals("OOO")) {
                winnerText.setText("O won!");
                buttons.forEach(this::resetButton);
                overallTurn = 0;
            }
            else if (overallTurn == 9) {
                winnerText.setText("It's a Draw");
                buttons.forEach(this::resetButton);
                overallTurn = 0;
            }
        }
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
