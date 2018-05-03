package net.idevcorp.simpleandroidapp.network;


import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private final static String BASE_URL = "https://api.stackexchange.com/2.2/";
    FirebaseAuth firebaseAuth;

    private RetrofitBuilder() {

    }

    private static Endpoints instance = null;

    public static Endpoints getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()
                    .create(Endpoints.class);
        }

        return instance;
    }

    private static OkHttpClient getHttpClient(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(httpLoggingInterceptor);
        return okHttpClient.build();
    }

}
