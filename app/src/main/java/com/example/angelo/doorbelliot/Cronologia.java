package com.example.angelo.doorbelliot;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;


/**
 * Created by angelo on 08/05/17.
 */

public class Cronologia extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "Cronologia";
    ArrayList<Bitmap> bitmaps;
    Animator animator;
    private int mShortAnimationDuration;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.crono,container,false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
         bitmaps=new ArrayList<>();
        mAdapter = new MyAdapter(bitmaps);
        mRecyclerView.setAdapter(mAdapter);


//modifica del daw: permette di vedere cosa accade su pressione di card_view
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                               Log.d("pressione"," avvenuta ");
                        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
                        Bitmap item = bitmaps.get(itemPosition);
                        Toast.makeText(getContext(), "immag"+itemPosition, Toast.LENGTH_LONG).show();


                    DecodeImage.zoomImageFromThumb(view1,R.id.info_image,mShortAnimationDuration,animator,);


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

//fine modifica del daw



    }


    public void addMessage(final Bitmap payload) {
        Log.d(TAG,"messaggio: " + payload);
        ((MyAdapter)mAdapter).add(payload);
        Log.d (TAG, String.valueOf(mAdapter.getItemCount()));
    }








}
