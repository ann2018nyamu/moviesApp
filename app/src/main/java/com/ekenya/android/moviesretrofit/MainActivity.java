package com.ekenya.android.moviesretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRv;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRv = findViewById(R.id.recyclerview);
        movieList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieAPI movieAPI = retrofit.create(MovieAPI.class);

        Call<JSONResponse> call = movieAPI.getMovies();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                movieList = new ArrayList<>(Arrays.asList(jsonResponse.getMoviz()));

                PutDataIntoRecyclerView(movieList);

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });

    }

    private void PutDataIntoRecyclerView(List<Movie> movieList) {
        moviesAdapter adaptery = new moviesAdapter(this, movieList);
        moviesRv.setLayoutManager(new LinearLayoutManager(this));
        moviesRv.setAdapter(adaptery);
    }
}