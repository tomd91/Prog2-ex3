package at.ac.fhcampuswien.fhmdb;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

public class WatchListController {

    @FXML
    public JFXListView movieListView;

    public void initialize(){
        System.out.println("Watchlist Controller initialized!");
    }
}
