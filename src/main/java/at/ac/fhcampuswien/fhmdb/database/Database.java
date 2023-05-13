package at.ac.fhcampuswien.fhmdb.database;

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

    private Database() {
        try {
            crateConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTabel();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getDatabese() {
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private static void crateConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL,USER,PASSWORD);
    }

    public static void createTabel() throws SQLException {

        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }

    public void testDB() throws SQLException {
        WatchlistMovieEntity watchlistMovieEntity = new WatchlistMovieEntity("Mensch","Hat Beine","Sport, Schach", 1290,"einLink",69,10);
        dao.create(watchlistMovieEntity);
    }
}
