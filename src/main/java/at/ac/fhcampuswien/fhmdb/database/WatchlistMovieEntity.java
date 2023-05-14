package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "watchlist")
public class WatchlistMovieEntity {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField()
    private String title;
    @DatabaseField()
    private  String description;
    @DatabaseField()
    private  String genres;
    @DatabaseField()
    private  int releaseYear;
    @DatabaseField()
    private  String imgUrl;
    @DatabaseField()
    private  int lengthInMinutes; // in minutes
    @DatabaseField()
    private double rating; // 0-10

    public WatchlistMovieEntity(){};

    public WatchlistMovieEntity(String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }
}
