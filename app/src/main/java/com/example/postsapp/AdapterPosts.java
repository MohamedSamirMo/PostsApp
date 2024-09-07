package com.example.postsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsapp.databinding.ItemPostBinding;

import java.util.ArrayList;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.Holder> {
    private ArrayList<postModel> list;
    public OnItemClickListener listener;

    public void setList(ArrayList<postModel> list) {
        this.list = list;
    }
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding=ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
       return list==null?0:list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;
        public Holder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                    listener.onItemClick(list.get(getLayoutPosition()).getId());
                }}
            });
        }
        public void bind(postModel model){
            binding.title.setText(model.getTitle());
            binding.body.setText(model.getBody());

        }
    }
    public interface OnItemClickListener{
        void onItemClick(int id);
    }
}
