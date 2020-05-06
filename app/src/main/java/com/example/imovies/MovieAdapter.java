package com.example.imovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imovies.databinding.ListMovieItemBinding;
import com.example.imovies.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private OnItemClickListener onItemClickListener;
    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListMovieItemBinding listMovieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.list_movie_item,
                parent, false
        );
        return new MovieViewHolder(listMovieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieArrayList.get(position);
        holder.listMovieItemBinding.setMovie(movie);

    }

    @Override
    public int getItemCount() {

        return movieArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ListMovieItemBinding listMovieItemBinding;

        public MovieViewHolder(ListMovieItemBinding listMovieItemBinding) {
            super(listMovieItemBinding.getRoot());
            this.listMovieItemBinding = listMovieItemBinding;
            listMovieItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(movieArrayList.get(position));

                    }


                }
            });

        }

    }

    public interface OnItemClickListener {

        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
        notifyDataSetChanged();
    }
}
