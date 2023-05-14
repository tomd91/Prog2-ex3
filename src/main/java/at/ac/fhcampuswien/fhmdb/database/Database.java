package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    public static final String DB_URL = "jdbc:h2:file: ./db/watchlistdb";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";

    private static ConnectionSource connectionSource;

    Dao<WatchlistMovieEntity,Long> dao;

    private static Database instance;

    private Database() throws DatabaseException {
        try {
            crateConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTabel();

        } catch (SQLException e) {
            throw new DatabaseException("Error creating database: " + e.getMessage());
        }
    }

    public static Database getDatabese() throws DatabaseException {
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private static void crateConnectionSource() throws DatabaseException {
        try {
            connectionSource = new JdbcConnectionSource(DB_URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create connection source" + e.getMessage());
        }
    }

    public static void createTabel() throws DatabaseException {
        try {
            TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException("Error creating table: " + e.getMessage());
        }
    }

    public void testDB() throws DatabaseException {
        try {
        WatchlistMovieEntity watchlistMovieEntity = new WatchlistMovieEntity("Mensch","Hat Beine","Sport, Schach", 1290,"einLink",69,10);
        dao.create(watchlistMovieEntity);
        } catch (SQLException e) {
            throw new DatabaseException("Error adding movie to database: " + e.getMessage());
        }
    }

    public Dao<WatchlistMovieEntity, Long> getDao() {
        return dao;
    }

}
