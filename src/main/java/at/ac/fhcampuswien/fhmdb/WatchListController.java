package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.ui.MovieCellWatchlist;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class WatchListController implements Initializable {

    @FXML
    public JFXListView watchListView;

    public WatchlistRepository watchlistRepository = new WatchlistRepository();

    protected ObservableList<WatchlistMovieEntity> observableWatchlistMovies = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Watchlist Controller initialized!");
        try {
            watchlistRepository.ContactRepository();
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        initializeState();
        initializeLayout();

    }

    public void initializeLayout() {
        watchListView.setItems(observableWatchlistMovies);   // set the items of the listview to the observable list
        watchListView.setCellFactory(movieListView -> {
            try {
                return new MovieCellWatchlist();
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
        }); // apply custom cells to the listview

    }

    public void initializeState() {
        List<WatchlistMovieEntity> result = null;
        try {
            result = watchlistRepository.getAllWatchlistMovieEntities();
        } catch (SQLException e) {
            System.out.println("cant get movies from database");
        }

        setObservableWatchlistMovies(result);

    }

    public void setObservableWatchlistMovies(List<WatchlistMovieEntity> watchlistMovieEntities){
        observableWatchlistMovies.clear();
        observableWatchlistMovies.addAll(watchlistMovieEntities);
    }
}
