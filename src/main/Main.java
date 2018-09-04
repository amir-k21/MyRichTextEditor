package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/view/HomePage.fxml"));
        primaryStage.setTitle("MyTextEditor");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(250);
        primaryStage.setMinWidth(225);
        //set the icon
        Image icon = new Image(getClass().getResourceAsStream("/main/resources/images/text-plus-icon.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
