module TicTacToeCastlelar {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.prefs;
    exports main.tictactoe;
    opens main.tictactoe.view;
    opens main.tictactoe.model;

}