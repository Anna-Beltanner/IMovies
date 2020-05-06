package com.example.imovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imovies.databinding.ActivityAddEditBinding;
import com.example.imovies.model.Movie;

public class AddEditActivity extends AppCompatActivity {

    private Movie movie;

    public static final String MOVIE_ID = "movieId";
    public static final String MOVIE_NAME = "movieName";
    public static final String MOVIE_DESC = "movieDesc";

    private ActivityAddEditBinding activityAddEditBinding;

    private AddEditActivityClickHandler addEditActivityClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        movie = new Movie();
        activityAddEditBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_add_edit);
        activityAddEditBinding.setMovie(movie);
        addEditActivityClickHandler = new AddEditActivityClickHandler(this);
        activityAddEditBinding.setClickHandler(addEditActivityClickHandler);

        Intent intent = getIntent();

        if(intent.hasExtra(MOVIE_ID)) {
            setTitle("Edit movie");

            movie.setMovieName(intent.getStringExtra(MOVIE_NAME));
            movie.setMovieDesc(intent.getStringExtra(MOVIE_DESC));

        }else{
            setTitle("Add movie");
        }
    }

    public class AddEditActivityClickHandler{

        Context context;

        public AddEditActivityClickHandler(Context context) {

            this.context = context;
        }

        public void onOkButtonClicked(View v) {

            if(movie.getMovieName() == null){

                Toast.makeText(context, "Please input the movie name", Toast.LENGTH_SHORT);
            }else {

                Intent intent = new Intent();
                intent.putExtra(MOVIE_NAME, movie.getMovieName());
                intent.putExtra(MOVIE_DESC, movie.getMovieDesc());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
