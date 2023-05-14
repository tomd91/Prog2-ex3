package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.ui.MovieCellWatchlist;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.util.List;

public class WatchListController {

    @FXML
    public JFXListView movieListView;

    private WatchlistRepository watchlistRepository = new WatchlistRepository();

    protected ObservableList<WatchlistMovieEntity> observableWatchlistMovies = FXCollections.observableArrayList();

    public void initialize(){
        System.out.println("Watchlist Controller initialized!");
        //initializeState();
        //initializeLayout();

    }

    public void initializeLayout() {
        movieListView.setItems(observableWatchlistMovies);   // set the items of the listview to the observable list
        movieListView.setCellFactory(movieListView -> new MovieCellWatchlist()); // apply custom cells to the listview

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
