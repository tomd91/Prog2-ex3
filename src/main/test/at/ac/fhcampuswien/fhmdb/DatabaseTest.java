package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.Database;

import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException {
        Database.getDatabese().testDB();

    }
}
