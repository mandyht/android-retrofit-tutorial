package edu.uci.swe264p.retrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author Mandy Tsai
 *
 * Source: The behavior of this class mocks ProgramListAdapter.java available in this directory
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<String[]> movies;

    MovieListAdapter(List<String[]> data) {
        this.movies = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title, releaseDate, vote, overview;

        ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.ivMovie);
            title = itemView.findViewById(R.id.tvTitle);
            releaseDate = itemView.findViewById(R.id.tvReleaseDate);
            vote = itemView.findViewById(R.id.tvVote);
            overview = itemView.findViewById(R.id.tvOverview);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] movie = movies.get(position);

        //holder.poster.setImageBitmap();

        holder.title.setText(movie[1]);
        holder.releaseDate.setText(movie[2]);
        holder.vote.setText(movie[3]);
        holder.overview.setText(movie[4]);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
