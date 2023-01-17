package main.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.tictactoe.view.GameInterfaceController;
import java.io.IOException;
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tic Tac Toe");
        this.primaryStage.getIcons().add(new Image("file:resources/índice.png"));
        initRootLayout();
        showGameInterface();
    }

    /**
     *Load RootLayout stage and display it from the FXML file
     */
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
         primaryStage.setResizable(false);

         // Give the controller access to the main app.
         //RootLayoutController controller = loader.getController();
         //controller.setMainApp(this);
         primaryStage.show();
    }

    /**
     *Load Game Interface layout and display it from a FXML file
     */
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
            controller.setMainApp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

