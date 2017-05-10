package com.example.angelo.doorbelliot;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by angelo on 09/05/17.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Bitmap> myCrono;



    public MyAdapter(ArrayList<Bitmap> image) {

        myCrono=image;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.info_image);

        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImageView.setImageBitmap(myCrono.get(position));

    }

    public void add(Bitmap data){
        myCrono.add(data);

        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return myCrono.size();
    }



}
