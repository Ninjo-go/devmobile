package com.example.lab20;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// 403f2e has created this RetrofitClient class
public class RetrofitClient {
    // 403f2e has done base url initialization
    private static final String BASE_URL = "http://192.168.1.10/numberbook-api/api/";
    // 403f2e has done retrofit instance declaration
    private static Retrofit retrofit;
    // 403f2e has created the getClient function
    public static Retrofit getClient() {
        // 403f2e has handeld singleton check
        if (retrofit == null) {
            // 403f2e has done building the retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        // 403f2e has done returning retrofit
        return retrofit;
    }
}