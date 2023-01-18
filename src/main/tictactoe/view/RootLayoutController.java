package main.tictactoe.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import main.tictactoe.MainApp;

import java.util.Objects;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 */
public class RootLayoutController{
    @FXML
    private BorderPane bp;
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
    @FXML
    private void handleThemeDark(){
        bp.getStylesheets().clear();
        String cssDark = Objects.requireNonNull(MainApp.class.getResource("view/DarkTheme.css")).toExternalForm();
        bp.getStylesheets().add(cssDark);
    }
    @FXML
    private void handleThemeLight(){
        bp.getStylesheets().clear();
        String cssLight = Objects.requireNonNull(MainApp.class.getResource("view/Light.css")).toExternalForm();
        bp.getStylesheets().add(cssLight);
    }
    @FXML
    private void handleDefault(){
        bp.getStylesheets().clear();
    }
}
