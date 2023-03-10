package main.tictactoe.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.tictactoe.MainApp;
import main.tictactoe.model.Person;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Controller of the GameInterface layout. Provides the basic function
 * from starting the game Tic Tac Toe to record statistics of different
 * players and Cpu. Contains the mentioned methods and other JavaFX elements.
 */
public class GameInterfaceController implements Initializable {
    public Button stats;
    private final ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Button> buttons;
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
    @FXML
    private ToggleGroup radioGroup = new ToggleGroup();
    private boolean gameOver = false;
    private boolean cpu1 = false;
    private boolean cpu2 = false;
    private String combination = "";

    /**
     *
     * Initilize the game by setting up all the buttons from the Grid pane and
     * starts the game on a clicked button.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9));
        humVscpu.setToggleGroup(radioGroup);
        humVshum.setToggleGroup(radioGroup);
        cpuVscpu.setToggleGroup(radioGroup);
        persons.add(new Person("Cpu"));
        for(Button b: buttons) b.setDisable(true);
        start.setOnAction(e -> {
            buttons.forEach(this::resetButton);
            for(Button b: buttons) b.setDisable(true);
            winnerText.setText(" ");
            gameOver = false;
            overallTurn = 0;
            for(Button b : buttons) b.setDisable(false);
            if (humVscpu.isSelected()){
                cpu1 = true;
                cpu2 = false;
                buttons.forEach(button -> {
                    setupButtonCpu(button);
                    button.setFocusTraversable(false);
                });
            }
            else if (humVshum.isSelected()){
                cpu1 = false;
                cpu2 = false;
                overallTurn = 0;
                playerTurn = 0;
                buttons.forEach(button ->{
                    setupButton(button);
                    button.setFocusTraversable(false);
                });
            }
            else if (cpuVscpu.isSelected()){
                cpu1 = true;
                cpu2 = true;
                setupButtonCpuvsCpu();
            }
        });
    }

    /**
     *On clicked button, opens a new window and load the statistics FXML file
     * and displays it on screen
     */
    @FXML
    public void handleStats(){
        showStatistics();
    }

    /**
     *Reset all buttons of the Grid pane, set all text to empty,
     * reboot all integer turns, and set all booleans by default.
     */
    @FXML
    void restartGame() {
        buttons.forEach(this::resetButton);
        for(Button b: buttons) b.setDisable(true);
        winnerText.setText(" ");
        gameOver = false;
        overallTurn = 0;
    }

    /**
     *Set all buttons text to empty and set them to disable.
     */
    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    /**
     *On selected option, starts the game with cpu
     * playing against cpu.
     */
    public void setupButtonCpuvsCpu(){
        while(!gameOver && overallTurn <8) {
            cpuMoves2();
            checkIfGameIsOver();
            cpuMoves();
            checkIfGameIsOver();
        }
        if(!gameOver && overallTurn == 8) {
            cpuMoves2();
            checkIfGameIsOver();
        }
    }

    /**
     *On selected option, starts the game with  cpu
     * versus human.
     */
    private void setupButtonCpu(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            button.setText("X");
            button.setDisable(true);
            ++overallTurn;
            if (overallTurn < 8) {
                cpuMoves();
                if(!gameOver)checkIfGameIsOver();
            }
            if(!gameOver)checkIfGameIsOver();
        });
    }

    /**
     *Set the moves of the cpu1 with random movements.
     */
    public void cpuMoves() {
        int move = randomMove();
        Button cpu = buttons.get(move);
        boolean disabled = false;
        for(Button b: buttons) if(b.isDisabled()) disabled = true;
        while (disabled && !cpu.getText().isEmpty() && cpu.isDisabled()){
            move = randomMove();
            cpu = buttons.get(move);
        }
        if(disabled && !cpu.isDisabled() && cpu.getText().isEmpty()){
        buttons.get(move).setText("O");
        buttons.get(move).setDisable(true);
        ++overallTurn;
        }
    }

    /**
     *Set the moves of the cpu2 with random movements, it's used
     * specifically for cpu versus cpu gameplay.
     */
    public void cpuMoves2() {
        int move = randomMove();
        Button cpu = buttons.get(move);
        boolean disabled = false;
        for(Button b: buttons) if(b.isDisabled()) disabled = true;
        while (disabled && !cpu.getText().isEmpty() && cpu.isDisabled()){
            move = randomMove();
            cpu = buttons.get(move);
        }
        if(!disabled && !cpu.isDisabled() && cpu.getText().isEmpty() && overallTurn < 2){
            buttons.get(move).setText("X");
            buttons.get(move).setDisable(true);
            ++overallTurn;
        }
        else if (disabled && !cpu.isDisabled() && cpu.getText().isEmpty() && overallTurn > 1) {
            buttons.get(move).setText("X");
            buttons.get(move).setDisable(true);
            ++overallTurn;
        }
    }

    /**
     *Method to genereate a random number of the list of buttons in the Grid pane.
     * @return Integer
     */
    public int randomMove(){
        return ThreadLocalRandom.current().nextInt(0,buttons.size());
    }

    /**
     *On selected option, starts the game into a human
     * versus human gameplay.
     */
    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            ++overallTurn;
            if(!gameOver) checkIfGameIsOver();

        });
    }

    /**
     *Set the text of a clicked button from the Grid pane,
     * could be X or O.
     */
    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            playerTurn = 1;
        } else{
            button.setText("O");
            playerTurn = 0;
        }
    }

    /**
     *Method to check winners and draws.
     */
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
            if (line.equals("XXX") && !gameOver) {
                combination = line;
                gameOver = true;
                winnerText.setText("X won!");
                for(Button b: buttons) b.setDisable(true);
                overallTurn = 0;
                if(cpu1 && !cpu2 || !cpu1 && !cpu2)winnerInsert();
            }
            //O winner
            else if (line.equals("OOO") && !gameOver) {
                combination = line;
                gameOver = true;
                winnerText.setText("O won!");
                for(Button b: buttons) b.setDisable(true);
                overallTurn = 0;
                if(cpu1 && !cpu2 || !cpu1 && !cpu2)winnerInsert();
            }
        }
        //DRAW
        if (!gameOver && overallTurn == 9) {
            combination = "DRAW";
            gameOver = true;
            winnerText.setText("It's a draw!");
            overallTurn = 0;
            if(cpu1 && !cpu2 || !cpu1 && !cpu2)winnerInsert();
        }
    }

    /**
     *Load a window to record the statistics for
     * the players after game over.
     */
    public void winnerInsert() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Winner.fxml"));
            AnchorPane winner = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL); //Possibility to not record stats
            dialogStage.initOwner(new Stage());
            Scene scene = new Scene(winner);
            dialogStage.setScene(scene);
            dialogStage.getIcons().add(new Image("file:resources/winner.jpg"));
            dialogStage.show();
            WinnerController controller = loader.getController();
            controller.showImage();
            if(cpu1) controller.turnOffTextO();
            controller.getSubmit().setOnMouseClicked(e -> {
                controller.addStats(persons, combination, cpu1);
                dialogStage.close();
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *Opens a window to show the statistics of the players, Cpu included.
     */
    public void showStatistics(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Statistics.fxml"));
            BorderPane stats = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistics");
            dialogStage.getIcons().add(new Image("file:resources/stats.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(new Stage());
            Scene scene = new Scene(stats);
            dialogStage.setScene(scene);
            dialogStage.show();
            ArrayList<Person> personsSorted = new ArrayList<>(persons);
            personsSorted.sort(Comparator.comparingInt(Person::getWin).reversed());
            ObservableList<Person> personData = FXCollections.observableArrayList(personsSorted);
            StatisticsController controller = loader.getController();
            controller.init(personData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *Is called by the main application to give a reference back to itself.
     */
    public void setMainApp() {
    }

}
