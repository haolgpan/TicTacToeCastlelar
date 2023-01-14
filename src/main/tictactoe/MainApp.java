package main.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.tictactoe.view.GameInterfaceController;
import main.tictactoe.view.StatisticsController;

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
        this.primaryStage.getIcons().add(new Image("file:resources/índice.png"));
        initRootLayout();
        showGameInterface();
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
        try {
            // Load tic tac toe.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GameInterface.fxml"));
            SplitPane gameInterface = loader.load();

            // Set tic tac toe into the center of root layout.
            rootLayout.setCenter(gameInterface);
            // Give the controller access to the main app.
            GameInterfaceController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showStatistics(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Statistics.fxml"));
            BorderPane stats = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(stats);
            dialogStage.setScene(scene);
            dialogStage.show();
            StatisticsController controller = loader.getController();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

