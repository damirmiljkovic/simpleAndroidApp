package net.idevcorp.simpleandroidapp.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private final static String BASE_URL = "https://api.stackexchange.com/2.2/";

    private RetrofitBuilder() {
    }

    private static Endpoints instance = null;

    public static Endpoints getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Endpoints.class);
        }

        return instance;
    }

    private static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }

}
