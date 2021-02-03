package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mandy Tsai
 *
 * Source: The behavior of this class mocks MainActivity.class available in the current directory
 */
public class MovieListActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "e5bd9d10f8c2d7add69851b08de20ed8";

    private static Retrofit retrofit = null;

    private List<String[]> movies = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        connect();

        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieListAdapter(movies));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void connect() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<TopRatedResponse> call = movieApiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<TopRatedResponse>() {
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {
                List<LinkedTreeMap> results = response.body().getResults();
                for (int i = 0; i < results.size(); i++) {
                    String[] array = new String[] {
                            results.get(i).get("poster_path").toString(),
                            results.get(i).get("title").toString(),
                            results.get(i).get("release_date").toString(),
                            String.valueOf(results.get(i).get("vote_average")),
                            results.get(i).get("overview").toString()
                    };

                    movies.add(array);
                }

                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable throwable) {
                Log.e(MovieListActivity.class.getSimpleName(), throwable.toString());
            }
        });
    }
}
