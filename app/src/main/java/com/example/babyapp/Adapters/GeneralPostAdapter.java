package com.example.babyapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.babyapp.DetailActivity;
import com.example.babyapp.Pages.GeneralPosts;
import com.example.babyapp.R;

import java.util.List;

public class GeneralPostAdapter extends RecyclerView.Adapter<GeneralPostAdapter.GeneralPostHolder> {

    private Context context;
    private List<GeneralPosts> generalPostList;

    public GeneralPostAdapter(Context context, List<GeneralPosts> generalPosts) {
        this.context = context;
        generalPostList = generalPosts;
    }

    @NonNull
    @Override
    public GeneralPostAdapter.GeneralPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent ,false);
        return new GeneralPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralPostAdapter.GeneralPostHolder holder, int position) {

        GeneralPosts generalPosts = generalPostList.get(position);
        holder.title.setText(generalPosts.getTitle());
        holder.highlight.setText(generalPosts.getHighlight());
        Glide.with(context).load(generalPosts.getImage()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", generalPosts.getTitle());
                bundle.putString("sititle", generalPosts.getSiTitle());
                bundle.putString("description", generalPosts.getDescription());
                bundle.putString("sidescription", generalPosts.getSiDescription());
                bundle.putString("image", generalPosts.getImage());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return generalPostList.size();
    }

    public class GeneralPostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, highlight;
        CardView cardView;

        public GeneralPostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.coverImage);
            title = itemView.findViewById(R.id.Title);
            highlight = itemView.findViewById(R.id.Highlight);
            cardView = itemView.findViewById(R.id.mainCard);

        }
    }
}
