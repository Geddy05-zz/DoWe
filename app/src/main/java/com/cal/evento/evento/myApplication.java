package com.cal.evento.evento;

import android.app.Application;
import android.content.res.Configuration;

//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

/**
 * Created by geddy on 21/12/15.
 */
public class MyApplication extends Application {

    ParseUser parseUser;


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
//        FacebookSdk.sdkInitialize(this);

        ParseFacebookUtils.initialize(this);
//        ParseFacebookUtils.initialize(getString(R.string.facebook_app_id));

//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
//        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
