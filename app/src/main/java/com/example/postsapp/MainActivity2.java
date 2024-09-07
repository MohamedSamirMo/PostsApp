package com.example.postsapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.postsapp.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

     AdapterComments adapter=new AdapterComments();
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//
//        int id=getIntent().getIntExtra("id",0);
//        RetrofitConnection.getApiCalls().getComments(id)
//                .enqueue(new Callback<List<commentModel>>() {
//                    @Override
//                    public void onResponse(Call<List<commentModel>> call, Response<List<commentModel>> response) {
//                        adapter.setList((ArrayList<commentModel>) response.body());
//                        binding.recComments.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<commentModel>> call, Throwable throwable) {
//
//                    }
//                });

        RetrofitConnection.getApiCalls().getComments(
                getIntent().getIntExtra("id",0)
                )
                .flatMapObservable(Observable::fromIterable)
                .filter(new Predicate<commentModel>() {
                    @Override
                    public boolean test(commentModel commentModel) throws Throwable {

                        return commentModel.getPostId()==1
                                || commentModel.getPostId()==2
                                || commentModel.getPostId()==3
                                ;
                    }
                }).toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<commentModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        binding.recComments.setAdapter(adapter);

                    }

                    @Override
                    public void onSuccess(@NonNull List<commentModel> commentModels) {
                        adapter.setList((ArrayList<commentModel>) commentModels);
                        binding.recComments.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                    }
                });





    }
}