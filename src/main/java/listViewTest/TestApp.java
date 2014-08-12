package listViewTest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestApp extends Application
{
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        BorderPane contentPane = new BorderPane();

        final TextArea outputArea = new TextArea();
        outputArea.setWrapText(true);
        contentPane.setCenter(outputArea);

        BorderPane lowerPane = new BorderPane();
        final TextArea inputField = new TextArea();
        inputField.setWrapText(true);
        inputField.setPrefRowCount(3);
        lowerPane.setCenter(inputField);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                String text = inputField.getText();
                outputArea.appendText(text);
                outputArea.appendText("\n");
            }
        });
        lowerPane.setRight(sendButton);
        contentPane.setBottom(lowerPane);

        Scene scene = new Scene(contentPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}