package com.example.postsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postsapp.databinding.ItemCommentBinding;
import com.example.postsapp.databinding.ItemPostBinding;

import java.util.ArrayList;

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.Holder> {
    private ArrayList<commentModel> list;


    public void setList(ArrayList<commentModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding binding=ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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

        private ItemCommentBinding binding;
        public Holder(ItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
        public void bind(commentModel model){
            binding.textName.setText(model.getName());
            binding.body.setText(model.getBody());

        }
    }

}
