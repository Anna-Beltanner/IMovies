package com.example.imovies.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imovies.model.Genre;
import com.example.imovies.model.Movie;
import com.example.imovies.model.Repository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    Repository repository;
    private LiveData<List<Genre>> genres;
    private LiveData<List<Movie>> genreMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }

    public LiveData<List<Genre>> getGenres() {
        genres = repository.getGenres();
        return genres;
    }

    public LiveData<List<Movie>> getGenreMovies(int genreId) {
        genreMovies = repository.getGenresMovies(genreId);
        return genreMovies;
    }

    public void addNewMovie(Movie movie){

        repository.insertMovie(movie);
    }

    public void updateMovie(Movie movie){

        repository.updateMovie(movie);
    }

    public void deleteMovie(Movie movie){

        repository.deleteMovie(movie);
    }

}

