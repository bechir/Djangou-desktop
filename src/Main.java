import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private String[] langs = {"Francais", "Anglais", "Poulaar", "Wolof", "Soninké"};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Djangou");

        StackPane root = new StackPane();

        GridPane mainGrid = new GridPane();

        ComboBox<String> sourceLangChoices = new ComboBox<>();
        sourceLangChoices.getItems().addAll(langs);
        sourceLangChoices.getSelectionModel().selectFirst();
        sourceLangChoices.setOnAction(e -> System.out.println("Langue: " + sourceLangChoices.getSelectionModel().getSelectedItem()));
        mainGrid.add(sourceLangChoices, 0, 0);

        ComboBox<String> targetLangChoices = new ComboBox<>();
        targetLangChoices.getItems().addAll(langs);
        targetLangChoices.getSelectionModel().select(1);
        targetLangChoices.setOnAction(e -> log("Langue cible: " + targetLangChoices.getSelectionModel().getSelectedItem()));
        mainGrid.add(targetLangChoices, 1, 0);

        TextField wordInput = new TextField("");
        wordInput.setPromptText("Entrez un mot ici");
        wordInput.setOnAction(e -> log("Mot: " + wordInput.getText()));
        mainGrid.add(wordInput, 0, 1);

//        Image submitIcon = new Image(getClass().getResourceAsStream("search.png"));
        Button submit = new Button("Rechercher");

        DropShadow btnShadow = new DropShadow();
        btnShadow.setRadius(5.0);
        btnShadow.setOffsetX(1.0);
        btnShadow.setOffsetY(1.0);
        btnShadow.setColor(Color.color(0.4, 0.5, 0.5));
        submit.addEventHandler(MouseEvent.MOUSE_ENTERED, (EventHandler<Event>)e -> {
            submit.setEffect(btnShadow);
        });
        submit.addEventHandler(MouseEvent.MOUSE_EXITED, (EventHandler<Event>)e -> {
            submit.setEffect(null);
        });
        submit.setOnMouseClicked((e) -> {
            log("Langue source: " + sourceLangChoices.getSelectionModel().getSelectedItem());
            log("Mot à traduire: " + wordInput.getText());
        });
        mainGrid.add(submit, 1, 1);

        mainGrid.setPadding(new Insets(20));

        mainGrid.setHgap(50);
        mainGrid.setVgap(20);

        root.getChildren().add(mainGrid);
        root.setAlignment(mainGrid, Pos.TOP_CENTER);

        Button quit = new Button("Quitter");
        quit.setOnAction(e -> primaryStage.close());
        root.getChildren().add(quit);
        root.setAlignment(quit, Pos.BOTTOM_RIGHT);

        primaryStage.setScene(new Scene(root, 420, 480));
        primaryStage.show();
    }

    private void log(String str) {
        System.out.println(str);
    }
}