package com.anubhav.ytaudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<model> dataSet;
    private Context mContext = null;
    private final OnItemClickListener listener;


    public Adapter(Context mContext, ArrayList<model> dataSet, OnItemClickListener listener) {
        this.dataSet = dataSet;
        this.mContext = mContext;
        this.listener = listener;

    }

    public Adapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Adapter(ArrayList<model> mListData, OnItemClickListener onItemClickListener, OnItemClickListener listener) {

        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        ViewHolder postHolder = new ViewHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textViewTitle = holder.textViewTitle;
        TextView textViewChannel= holder.textViewChannel;
        ImageView ImageThumb = holder.ImageThumb;

        model object = dataSet.get(position);
        textViewChannel.setText(object.getChannelTitle());
        textViewTitle.setText((object.getTitle()));

        Picasso.get().load(object.getThumbnail()).into(ImageThumb);
    }

    @Override
    public int getItemCount() {

        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewChannel;
        ImageView ImageThumb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textViewTitle=(TextView)itemView.findViewById(R.id.textViewTitle);
            this.textViewChannel=(TextView)itemView.findViewById(R.id.textViewChannel);
            this.ImageThumb=(ImageView) itemView.findViewById(R.id.ImageThumb);


        }
    }
}

