package com.sprint3r.ronin.udemyfeedreader;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class retrofitApi {

    final private String API_URL = "https://www.udemy.com";


    public IUdemyCourses getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUdemyCourses service = retrofit.create(IUdemyCourses.class);
        return service;
    }
}
