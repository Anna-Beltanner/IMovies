package com.example.imovies.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.imovies.MovieDao;

@Database(entities = {Genre.class, Movie.class}, version = 1)

public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase INSTANCE;

    public abstract GenreDao getGenreDao();

    public abstract MovieDao getMovieDao();

    public static synchronized MovieDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, "movie_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();

        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitDataAsyncTask(INSTANCE).execute();
        }
    };

    private static class InitDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private GenreDao genreDao;
        private MovieDao movieDao;

        public InitDataAsyncTask(MovieDatabase database) {

            genreDao = database.getGenreDao();
            movieDao = database.getMovieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Genre comedyGenre = new Genre();
            comedyGenre.setGenreName("Comedy");

            Genre dramaGenre = new Genre();
            dramaGenre.setGenreName("Drama");

            Genre adventureGenre = new Genre();
            adventureGenre.setGenreName("Adventure");

            genreDao.insert(comedyGenre);
            genreDao.insert(dramaGenre);
            genreDao.insert(adventureGenre);

            Movie movie1 = new Movie();
            movie1.setMovieName("Home Alone");
            movie1.setMovieDesc("An eight-year-old Kevin must protect his house from a pair of burglars when he is accidentally left home alone by his family during Christmas vacation");
            movie1.setMovieID(1);

            Movie movie2 = new Movie();
            movie2.setMovieName("Pearl Harbor");
            movie2.setMovieDesc("A tale of war and romance mixed in with history. The story follows two lifelong friends and a beautiful nurse who are caught up in the horror of an infamous Sunday morning in 1941");
            movie2.setMovieID(2);

            Movie movie3 = new Movie();
            movie3.setMovieName("Harry Potter and The Philosopher's stone");
            movie3.setMovieDesc("The first novel in the Harry Potter series and Rowling's debut novel, it follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry");
            movie3.setMovieID(3);

            movieDao.insert(movie1);
            movieDao.insert(movie2);
            movieDao.insert(movie3);

            return null;
        }
    }
}

