package com.tt.imageloaderdemo;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by tuozhaobing on 15-12-11.
 * Add Some Comment
 */
public class ImageLoaderApplication extends Application {
    private static final String TAG = "ImageLoaderApplication";
    @Override
    public void onCreate() {
        super.onCreate();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);
    }
}
