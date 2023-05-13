package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;

    public void ContactRepository() {
        this.dao = Database.getDatabese().getDao();
    }

    public void addMovie(Movie movie) throws SQLException {
        WatchlistMovieEntity movieToAdd = new WatchlistMovieEntity(
                movie.getTitle(),
                movie.getDescription(),
                movie.getGenresAssString(),
                movie.getReleaseYear(),
                movie.getImgUrl(),
                movie.getLengthInMinutes(),
                movie.getRating()
        );

        dao.create(movieToAdd);
    }

    public void removeMovie() {
        return;
    }

    public Movie[] getMovies() {
        return new Movie[]{};
    }


}
