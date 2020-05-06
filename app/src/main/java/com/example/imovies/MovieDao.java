package com.example.imovies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imovies.model.Movie;

import java.util.List;

@Dao public interface MovieDao {

    @Insert
    void insert(Movie movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("select * from movie_table")
    LiveData<List<Movie>> getAllMovies();

    @Query("select * from movie_table where genre_id==:genreId")
    LiveData<List<Movie>> getGenreMovies(int genreId);


}
