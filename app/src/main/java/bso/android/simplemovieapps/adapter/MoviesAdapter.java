package bso.android.simplemovieapps.adapter;

/**
 * Created by MUL22 on 3/22/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bso.android.simplemovieapps.activity.DetailMovieActivity;
import bso.android.simplemovieapps.activity.LihatDataActivity;
import bso.android.simplemovieapps.model.Movie;
import bso.android.simplemovieapps.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre,id;
        CardView cvMovie;

        public MyViewHolder(View view) {
            super(view);
            id =  (TextView) view.findViewById(R.id.id);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            cvMovie = (CardView) view.findViewById(R.id.cv_movie);
        }
    }


    public MoviesAdapter(List<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movie movie = moviesList.get(position);
        holder.id.setText(movie.getId());
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(context, DetailMovieActivity.class);
                i.putExtra("title",movie.getTitle());
                i.putExtra("genre",movie.getGenre());
                i.putExtra("year",movie.getYear());
                context.startActivity(new Intent(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}