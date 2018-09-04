package main.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;

public class HomePageController {
    private File file;
    @FXML
    private HTMLEditor htmleditorid;

    @FXML
    public void closeTheProgram(ActionEvent event) {
        Stage stage = (Stage) htmleditorid.getScene().getWindow();
        stage.close();
    }

    @FXML
    void showAboutMe(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About us");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        //to clear all the buttons and the functionality of close buton you should do this
        //but for now we don't need that.
        //alert.getButtonTypes().clear();
        alert.setContentText("Write your about me here!");
        alert.showAndWait();
    }

    @FXML
    public void openFile(ActionEvent event) {
        //create a new file chooser
        FileChooser fileChooser = new FileChooser();
        //Set extension filter for our file chooser
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Plain Text (*.TXT)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 =
                new FileChooser.ExtensionFilter("Rich Text (*.RTF)", "*.rtf");
        FileChooser.ExtensionFilter extFilter3 =
                new FileChooser.ExtensionFilter("Web Page (*.HTML)", "*.html");
        FileChooser.ExtensionFilter extFilter4 =
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(extFilter4,extFilter,extFilter2,extFilter3);
        //Show save file dialog
        //first get the scene so we could show a file chooser dialogue on the stage
        Stage stage = (Stage) htmleditorid.getScene().getWindow();
        //set the file to null
        file = null;
        file = fileChooser.showOpenDialog(stage);
        //if there is a file selected continue and process the saving
        if(file != null) {
            if(readfile()!=null) {
                htmleditorid.setHtmlText(readfile());
                //then immidiately set the file to null so we wouldn't override the poor file
                file = null;
            }else{
                //set the message directly on the html editor.
                //you could make a label on the bottom
                //but it looks more fun :)
                htmleditorid.setHtmlText("Sorry Couldn't open your file!");
            }
        }
    }

    //a method to add a html break for each line
    private String readfile() {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while((text = bufferedReader.readLine())!=null){
                stringBuilder.append(text + "<br/>");
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveText(ActionEvent event) {
        //create a new file chooser
        FileChooser fileChooser = new FileChooser();
        //Set extension filter for our file chooser
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Plain Text (*.TXT)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 =
                new FileChooser.ExtensionFilter("Rich Text (*.RTF)", "*.rtf");
        FileChooser.ExtensionFilter extFilter3 =
                new FileChooser.ExtensionFilter("Web Page (*.HTML)", "*.html");
        FileChooser.ExtensionFilter extFilter4 =
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(extFilter,extFilter2,extFilter3,extFilter4);
        //Show save file dialog
        //first get the scene so we could show a file chooser dialogue on the stage
        Stage stage = (Stage) htmleditorid.getScene().getWindow();
        //Check if the file is null
        if (file ==null){
            //create a new file
            file = fileChooser.showSaveDialog(stage);
            //if there is a file selected continue and process the saving
            if(file != null) {
                try {
                    //create a new filewriter to write to our file
                    FileWriter fileWriter = new FileWriter(file);
                    //for each item in the table(each row)
                    fileWriter.write(htmleditorid.getHtmlText());
                    //always remember to close the filewriter
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //now that we already have chosen a file just save the file
            try {
                //create a new filewriter to write to our file
                FileWriter fileWriter = new FileWriter(file);
                //for each item in the table(each row)
                fileWriter.write(htmleditorid.getHtmlText());
                //always remember to close the filewriter
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void saveAsText(ActionEvent event) {
        //create a new file chooser
        FileChooser fileChooser = new FileChooser();
        //Set extension filter for our file chooser
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Plain Text (*.TXT)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 =
                new FileChooser.ExtensionFilter("Rich Text (*.RTF)", "*.rtf");
        FileChooser.ExtensionFilter extFilter3 =
                new FileChooser.ExtensionFilter("Web Page (*.HTML)", "*.html");
        FileChooser.ExtensionFilter extFilter4 =
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(extFilter,extFilter2,extFilter3,extFilter4);
        //Show save file dialog
        //first get the scene so we could show a file chooser dialogue on the stage
        Stage stage = (Stage) htmleditorid.getScene().getWindow();

        //create a new file
        file = fileChooser.showSaveDialog(stage);
        //if there is a file selected continue and process the saving
        if(file != null) {
            try {
                //create a new filewriter to write to our file
                FileWriter fileWriter = new FileWriter(file);
                //for each item in the table(each row)
                fileWriter.write(htmleditorid.getHtmlText());
                //always remember to close the filewriter
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //set the stage to full screen (TOGGLE)
    @FXML
    public void setToFullScreen(ActionEvent event) {
        Stage stage = (Stage) htmleditorid.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }


    //clean up the page for a new start
    @FXML
    public void startNewFile(ActionEvent event) {
        file = null;
        htmleditorid.setHtmlText("");
    }

}
