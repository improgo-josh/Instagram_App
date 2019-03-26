package com.example.joshi.instagramapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {
    private static String APP_ID = "josh-instagram-codepath";
    private static String MASTER_KEY = "josh-instagram-codepath-MASTER";
    private static String MONGODB_URI = "mongodb://heroku_2jdxt398:v8av80khtsqfb5mee9dmnto066@ds117816.mlab.com:17816/heroku_2jdxt398";
    private static String PARSE_MOUNT = "/parse";
    private static String SERVER_URL = "https://josh-instagram-codepath.herokuapp.com/parse";

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        // Use for troubleshooting -- remove this line for production
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
/*
        // Use for monitoring Parse OkHttp traffic
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);*/

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APP_ID) // should correspond to APP_ID env variable
                .clientKey(null)  // set explicitly unless clientKey is explicitly configured on Parse server
//                .clientBuilder(builder)
                .server(SERVER_URL).build());

       /* // New test creation of object below
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
    }
}