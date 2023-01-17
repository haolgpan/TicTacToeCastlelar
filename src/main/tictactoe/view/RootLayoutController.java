package main.tictactoe.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController {
    /**
     *Close the aplication and all the opened windows.
     */
    @FXML
    private void handleQuit(){
        Platform.exit();
    }

    /**
     *Opens an about dialog.
     */
    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText("About");
        alert.setContentText("Author: Hao Pan\nPrototipe of the game Tic Tac Toe: \nhttps://github.com/haolgpan/TicTacToeCastlelar");
        alert.showAndWait();
    }
}
