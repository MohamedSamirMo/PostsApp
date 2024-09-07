package com.example.postsapp;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("posts")
    Single<List<postModel>> getPosts();
    @GET("comments")
    Single<List<commentModel>> getComments(@Query("postId") int id);

    // add new post with title and body
//    @POST("posts/{id}")
//    Call<postModel> addPost(@Body LoginModel model);
//
//    @FormUrlEncoded
//    @POST("posts/{id}")
//     Call<postModel> updatePost(@Field("title") String title, @Field("body") String body);
     // Call<postModel> addPost(@FieldMap Map<String, String> fields);
}
