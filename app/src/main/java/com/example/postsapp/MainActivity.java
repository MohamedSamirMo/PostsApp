package com.example.postsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.postsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AdapterPosts adapter= new AdapterPosts();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter.setListener(new AdapterPosts.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
//        RetrofitConnection.getApiCalls().getPosts()
//                .enqueue(new Callback<List<postModel>>() {
//                    @Override
//                    public void onResponse(Call<List<postModel>> call, Response<List<postModel>> response) {
//                        Log.d("TAG", "onResponse: ");
//                        adapter.setList((ArrayList<postModel>) response.body());
//                        binding.recposts.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<postModel>> call, Throwable throwable) {
//                        Toast.makeText(MainActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });


        RetrofitConnection.getApiCalls().getPosts()
                .flatMapObservable(Observable::fromIterable)
                .filter(new Predicate<postModel>() {
                    @Override
                    public boolean test(postModel postModel) throws Throwable {
                        return postModel.getUserId()==1;
                    }
                }).toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<postModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<postModel> postModels) {
                        adapter.setList((ArrayList<postModel>) postModels);
                        binding.recposts.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }
}