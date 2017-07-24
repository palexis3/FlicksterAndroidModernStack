package com.codepath.flickster.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private String mBaseUrl;

    // Module constructor
    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides @Singleton
    Gson providesGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Provides @Singleton
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
