import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Djangou");

        Button quit = new Button("Quitter");
        quit.setOnAction(e -> primaryStage.close());

        StackPane root = new StackPane();
        root.getChildren().add(quit);
        primaryStage.setScene(new Scene(root, 420, 480));
        primaryStage.show();
    }
}