package com.codepath.flickster.networking;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetModule {

    // we pass along the base url when we create NetModule because we need it so we can build the Retrofit instance.
    // Once Retrofit recognizes a @Inject annotation, this is where it provides from
    private String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Provides @Singleton
    // Gson argument is provided by the method above and it works the same way
    // for all other methods
    Retrofit providesRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides @Singleton
    MovieApiInterface providesApiInterface(Retrofit retrofit) {
        return retrofit.create(MovieApiInterface.class);
    }

    @Provides @Singleton
    MovieRestClient provideClient() {
        return new MovieRestClient();
    }
}
