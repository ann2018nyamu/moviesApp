package com.ekenya.android.moviesretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class moviesAdapter extends RecyclerView.Adapter<moviesAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<Movie> movieList;

    public moviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(movieList.get(position).getId()));
        holder.title.setText(movieList.get(position).getName());

        // Adding Glide Library to display the images
        Glide.with(mContext)
                .load(movieList.get(position).getImage())
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,id;
        ImageView img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            id = itemView.findViewById(R.id.description);
            img = itemView.findViewById(R.id.imageView);

        }
    }


}
