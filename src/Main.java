import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

import java.io.FileInputStream;

public class Main extends Application {
    ComboBox<String> sourceLangChoices = new ComboBox<>();
    ComboBox<String> targetLangChoices = new ComboBox<>();
    Label result = new Label();
    TextField wordInput = new TextField("");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Djangou");

        StackPane root = new StackPane();

        GridPane mainGrid = new GridPane();

        sourceLangChoices.getItems().addAll(Translator.getLanguages());
        sourceLangChoices.getSelectionModel().selectFirst();
        sourceLangChoices.setOnAction(e -> System.out.println("Langue: " + sourceLangChoices.getSelectionModel().getSelectedItem()));
        mainGrid.add(sourceLangChoices, 0, 0);

        targetLangChoices.getItems().addAll(Translator.getLanguages());
        targetLangChoices.getSelectionModel().select(1);
        targetLangChoices.setOnAction(e -> log("Langue cible: " + targetLangChoices.getSelectionModel().getSelectedItem()));
        mainGrid.add(targetLangChoices, 1, 0);

        wordInput.setPromptText("Entrez un mot ici");
        mainGrid.add(wordInput, 0, 1);

        Image submitIcon = new Image(new FileInputStream("search.png"));
        Button submit = new Button("Rechercher", new ImageView(submitIcon));
        submit.setStyle("-fx-background-color: #007bff; -fx-text-fill: #fff;");

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

        mainGrid.add(submit, 1, 1);

        Label resultLabel = new Label("Résultats");
        mainGrid.add(resultLabel, 0, 3);
        result.setLabelFor(resultLabel);
        mainGrid.add(result, 0, 4);

        submit.setOnMouseClicked(e -> processTranslation());
        wordInput.setOnAction(e -> processTranslation());

        mainGrid.setPadding(new Insets(20));

        mainGrid.setHgap(50);
        mainGrid.setVgap(20);

        root.getChildren().add(mainGrid);
        root.setAlignment(mainGrid, Pos.TOP_CENTER);

        Button quit = new Button("Quitter");
        quit.setPadding(new Insets(10, 30, 10, 30));
        quit.setStyle("-fx-background-color: rgb(221, 60, 75); -fx-text-fill: #fff;");
        quit.setOnAction(e -> primaryStage.close());
        root.getChildren().add(quit);
        root.setAlignment(quit, Pos.BOTTOM_RIGHT);

        primaryStage.setScene(new Scene(root, 420, 480));
        primaryStage.show();
    }

    private void log(String str) {
        System.out.println(str);
    }

    public void processTranslation()
    {
        String translation = Translator.translate(
                Translator.Language.valueOf(sourceLangChoices.getSelectionModel().getSelectedItem()),
                Translator.Language.valueOf(targetLangChoices.getSelectionModel().getSelectedItem()),
                wordInput.getText()
        );

        result.setText("Traduction: " + translation);
    }
}