package tuition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This is the main class. This is used to set up the GUI and display.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class Main extends Application {

    static Controller controller;

    /**
     * Start the GUI. Loads the fxml, sets up the stage and displays the stage.
     *
     * @param primaryStage PrimaryStage
     * @throws Exception when fxml file is not found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tuition Manager.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        Scene scene = new Scene(root);
        controller = loader.getController();
        controller.initialize();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tuition Manager");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
