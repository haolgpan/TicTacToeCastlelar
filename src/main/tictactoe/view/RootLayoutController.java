package main.tictactoe.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RootLayoutController {
    @FXML
    private void handleQuit(){
        Platform.exit();
    }
    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText("About");
        alert.setContentText("Author: Hao Pan\nPrototipe of the game Tic Tac Toe: \nhttps://github.com/haolgpan/TicTacToeCastlelar");
        alert.showAndWait();
    }
}
