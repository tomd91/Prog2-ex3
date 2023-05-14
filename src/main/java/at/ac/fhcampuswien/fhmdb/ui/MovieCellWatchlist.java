package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class MovieCellWatchlist extends ListCell<WatchlistMovieEntity> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final JFXButton removeBtn = new JFXButton("Remove");
    private final VBox layout = new VBox(title, detail, genre, removeBtn);

    private boolean collapsedDetails = true;

    private WatchlistMovieEntity watchlistMovieEntity;

    private WatchlistRepository watchlistRepository = new WatchlistRepository();



    public MovieCellWatchlist() {
        super();
        watchlistRepository.ContactRepository();

        // color scheme
        removeBtn.setStyle("-fx-background-color: #f5c518;");
        title.getStyleClass().add("text-yellow");
        detail.getStyleClass().add("text-white");
        genre.getStyleClass().add("text-white");
        genre.setStyle("-fx-font-style: italic");
        layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

        // layout
        title.fontProperty().set(title.getFont().font(20));
        detail.setWrapText(true);
        layout.setPadding(new Insets(10));
        layout.spacingProperty().set(10);
        layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);

        removeBtn.setOnMouseClicked(mouseEvent -> {
            //remove movie from watchlist

            try {
                watchlistRepository.removeWatchlistMovieEntities(getItem());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            removeBtn.setText("removed");

        });
    }

    private VBox getDetails() {
        VBox details = new VBox();
        Label releaseYear = new Label("Release Year: " + getItem().getReleaseYear());
        Label length = new Label("Length: " + getItem().getLengthInMinutes() + " minutes");
        Label rating = new Label("Rating: " + getItem().getRating() + "/10");

        releaseYear.getStyleClass().add("text-white");
        length.getStyleClass().add("text-white");
        rating.getStyleClass().add("text-white");

        details.getChildren().add(releaseYear);
        details.getChildren().add(rating);
        details.getChildren().add(length);

        return details;
    }
    @Override
    protected void updateItem(WatchlistMovieEntity watchlistMovieEntity, boolean empty) {
        super.updateItem(watchlistMovieEntity, empty);

        this.watchlistMovieEntity = watchlistMovieEntity;

        if (empty || watchlistMovieEntity == null) {
            setGraphic(null);
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(watchlistMovieEntity.getTitle());
            detail.setText(
                    watchlistMovieEntity.getDescription() != null
                            ? watchlistMovieEntity.getDescription()
                            : "No description available"
            );

            String genres = watchlistMovieEntity.getGenres();

            genre.setText(genres);

            detail.setMaxWidth(this.getScene().getWidth() - 30);

            setGraphic(layout);
        }
    }
}

