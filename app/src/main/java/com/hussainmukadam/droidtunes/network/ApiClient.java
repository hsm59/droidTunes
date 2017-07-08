package com.hussainmukadam.droidtunes.network;

import com.hussainmukadam.droidtunes.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hussain on 6/7/17.
 */

public class ApiClient {
//    private static final String BASE_URL = "https://itunes.apple.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
