package com.codepath.flickster.networking;

import com.codepath.flickster.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface AppComponent {

    // we need these for every Activity/Fragment/View/Object that has
    // a variable with the @Inject annotation

    // we need this for @Inject MovieRestClient inside MainActivity.java
    void inject(MainActivity activity);

    // we need this for @Inject MovieApiInterface in MovieRestClient.java
    void inject(MovieRestClient client);

}
