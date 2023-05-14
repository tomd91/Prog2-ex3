package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;

    public void ContactRepository() throws DatabaseException {
        this.dao = Database.getDatabese().getDao();
    }

    public void addMovie(Movie movie) throws DatabaseException {
        try {
            dao.create(castMovieToWatchlistMovieEntity(movie));
        } catch (SQLException e) {
            throw new DatabaseException("Failed to add movie to database: " + e.getMessage());
        }
    }

    public void removeMovie(Movie movie) throws DatabaseException {
        try {
            dao.delete(castMovieToWatchlistMovieEntity(movie));
        } catch (SQLException e) {
            throw new DatabaseException("Failed to remove movie from database: " + e.getMessage());
        }
    }

    public void removeWatchlistMovieEntities(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException {
        try {
            dao.delete(watchlistMovieEntity);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to remove movie from database: " + e.getMessage());
        }

    }

    public List<WatchlistMovieEntity> getAllWatchlistMovieEntities() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to get all watchlist movie entities from database: " + e.getMessage());
        }
    }

    private WatchlistMovieEntity castMovieToWatchlistMovieEntity(Movie movie) {
        return new WatchlistMovieEntity(
                movie.getTitle(),
                movie.getDescription(),
                movie.getGenresAssString(),
                movie.getReleaseYear(),
                movie.getImgUrl(),
                movie.getLengthInMinutes(),
                movie.getRating()
        );
    }
}
