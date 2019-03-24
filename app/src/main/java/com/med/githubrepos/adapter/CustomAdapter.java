package com.med.githubrepos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.med.githubrepos.Utils;
import com.med.githubrepos.model.ReposResponse;
import com.squareup.picasso.Picasso;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.med.githubrepos.R;
import com.med.githubrepos.model.Repo;


import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Repo> dataList;

    public CustomAdapter(List<Repo> dataList){
        this.dataList = new ArrayList<>(dataList);
    }

    public void setDataList(List<Repo> dataList) {
        this.dataList.addAll(dataList);
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.repo_item, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder customViewHolder, int i) {
        customViewHolder.name.setText(dataList.get(i).getName());
        customViewHolder.username.setText(dataList.get(i).getOwner().getName());
        customViewHolder.description.setText(dataList.get(i).getDescription());
        customViewHolder.numberStars.setText(Utils.getShortNumber(dataList.get(i).getStars()));

        Picasso.get()
                .load(dataList.get(i).getOwner().getAvatarURL())
                .placeholder(R.drawable.avatar_holder)
                .into(customViewHolder.userImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView name;
        TextView username;
        TextView description;
        ImageView userImage;
        TextView numberStars;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            name = mView.findViewById(R.id.name);
            username = mView.findViewById(R.id.username);
            description = mView.findViewById(R.id.description);
            userImage = mView.findViewById(R.id.user_image);
            numberStars = mView.findViewById(R.id.number_stars);
        }
    }
}
