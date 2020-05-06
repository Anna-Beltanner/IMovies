package com.example.imovies.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table", foreignKeys = @ForeignKey(entity = Genre.class, parentColumns = "id", childColumns = "genre_id", onDelete = ForeignKey.CASCADE))
public class Movie extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    private int movieID;
    @ColumnInfo(name = "movie_name")
    private String movieName;
    @ColumnInfo(name = "movie_desc")
    private String movieDesc;
    @ColumnInfo(name = "genre_id")
    private int genreId;

    public Movie() {
    }

    public Movie(int movieID, String movieName, String movieDesc, int genreId) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieDesc = movieDesc;
        this.genreId = genreId;
    }

    @Bindable
    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
        notifyPropertyChanged(BR.movieID);


    }

    @Bindable
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
        notifyPropertyChanged(BR.movieName);
    }

    @Bindable
    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
        notifyPropertyChanged(BR.movieDesc);
    }

   @Bindable
   public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
        notifyPropertyChanged(BR.genreId);
    }
}
