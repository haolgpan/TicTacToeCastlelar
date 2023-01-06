package main.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tic Tac Toe");
        initRootLayout();
        //showPersonOverview();
    }
    public void initRootLayout() {
         // Load root layout from fxml file.
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Show the scene containing the root layout.
         Scene scene = new Scene(rootLayout);
         primaryStage.setScene(scene);

         // Give the controller access to the main app.
         //RootLayoutController controller = loader.getController();
         //controller.setMainApp(this);
         primaryStage.show();
    }
    public void showGameInterface(){

    }
}

