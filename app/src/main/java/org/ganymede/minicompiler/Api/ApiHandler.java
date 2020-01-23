package org.ganymede.minicompiler.Api;

import org.ganymede.minicompiler.BuildConfig;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiHandler {

    private static final String BASE_URL = "https://api.jdoodle.com/v1/";

    public static final String API_ID = BuildConfig.API_ID;
    public static final String API_SECRET = BuildConfig.API_SECRET;
    public static final String LANGUAGE = "ruby";
    public static final String VERSION_INDEX = "3";


    private static Retrofit retrofit;

    public static ApiService getRetrofitInstance(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit.create(ApiService.class);
    }

}
