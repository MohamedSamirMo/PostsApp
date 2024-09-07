package com.example.postsapp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    private  static Retrofit retrofit;
    private synchronized static Retrofit getRetrofit(){
        if(retrofit==null){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();}
        return retrofit;

    }
    public static ApiCalls getApiCalls(){
        return getRetrofit().create(ApiCalls.class);
    }
}
