package com.codepath.flickster;

import android.app.Application;
import android.content.Context;

import com.codepath.flickster.networking.AppComponent;
import com.codepath.flickster.networking.AppModule;
import com.codepath.flickster.networking.NetModule;

public class MovieApp extends Application {

    private AppComponent mAppComponent;
    private static Context context;

    public MovieApp() {
        context = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                // list of modules that are part of this component need to be created here
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://api.themoviedb.org/3/"))
                .build();
    }

    public static Object getAppContext() {
        return context;
    }

    // we need this to inject with dagger inside custom objects
    public static MovieApp getApp() {
        return (MovieApp) getAppContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
