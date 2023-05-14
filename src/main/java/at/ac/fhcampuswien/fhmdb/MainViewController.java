package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainViewController {

    @FXML
    public JFXButton homeBtn;

    @FXML
    public JFXButton watchlistBtn;

    @FXML
    public JFXButton aboutBtn;

    @FXML
    public BorderPane mainPane;

    @FXML
    public Label errorLabel;



    public void initialize(){
        System.out.println("MainView Controller initialized");
        homeBtnClicked();
    }

    public void homeBtnClicked() {
        System.out.println("home button clicked");
        setContentView("home-view.fxml");
    }

    public void watchlistBtnClicked() {
        System.out.println("watchlist button clicked");
        setContentView("watchlist-view.fxml");
    }

    public void aboutBtnClicked() {
        System.out.println("about button clicked");
        setContentView("about-view.fxml");
    }

    public void setContentView(String pathToView){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathToView));

        try {
            mainPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("error - url not found ");
            System.out.println(e);
        }
    }

    public void setErrorLabel(Exception e){
        if (e instanceof DatabaseException) {
            errorLabel.setText(((DatabaseException) e).getMessage());
        } else if (e instanceof MovieApiException) {
            errorLabel.setText(((MovieApiException) e).getMessage());
        } else {
            errorLabel.setText("An error has occurred.");
        }
    }

}
