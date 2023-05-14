package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;

    public void ContactRepository() {
        this.dao = Database.getDatabese().getDao();
    }

    public void addMovie(Movie movie) throws SQLException {
        dao.create(castMovieToWatchlistMovieEntity(movie));
    }

    public void removeMovie(Movie movie) throws SQLException {
        dao.delete(castMovieToWatchlistMovieEntity(movie));
    }

    public void removeWatchlistMovieEntities(WatchlistMovieEntity watchlistMovieEntity) throws SQLException {
        dao.delete(watchlistMovieEntity);
    }

    public List<WatchlistMovieEntity> getAllWatchlistMovieEntities() throws SQLException {
        return dao.queryForAll();
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
