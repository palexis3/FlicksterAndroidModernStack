package com.codepath.flickster.networking;

import com.codepath.flickster.models.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRestClient {

    private final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private final String BASE_URL = "http://api.themoviedb.org/3/";

    // create an implementation of the API endpoints defined by the Interface that we created
    private MovieApiInterface movieApiInterface;

    public MovieRestClient() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieApiInterface = client.create(MovieApiInterface.class);
    }

    public void nowPlaying(Callback<MovieResponse> responseHandler) {
        Call<MovieResponse> call = movieApiInterface.getNowPlayingMovies(API_KEY);
        call.enqueue(responseHandler);
    }
}
