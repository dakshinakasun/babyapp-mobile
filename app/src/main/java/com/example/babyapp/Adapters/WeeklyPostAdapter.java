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
import com.example.babyapp.Pages.WeeklyPosts;
import com.example.babyapp.R;

import java.util.List;

public class WeeklyPostAdapter extends RecyclerView.Adapter<WeeklyPostAdapter.WeeklyPostHolder> {

    private Context context;
    private List<WeeklyPosts> weeklyPostList;

    public WeeklyPostAdapter(Context context, List<WeeklyPosts> weeklyPosts) {
        this.context = context;
        weeklyPostList = weeklyPosts;
    }


    @NonNull
    @Override
    public WeeklyPostAdapter.WeeklyPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent ,false);
        return new WeeklyPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyPostAdapter.WeeklyPostHolder holder, int position) {

        WeeklyPosts weeklyPosts = weeklyPostList.get(position);
        holder.title.setText(weeklyPosts.getTitle());
        holder.highlight.setText(weeklyPosts.getHighlight());
        Glide.with(context).load(weeklyPosts.getImage()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", weeklyPosts.getTitle());
                bundle.putString("sititle", weeklyPosts.getSiTitle());
                bundle.putString("description", weeklyPosts.getDescription());
                bundle.putString("sidescription", weeklyPosts.getSiDescription());
                bundle.putString("image", weeklyPosts.getImage());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weeklyPostList.size();
    }

    public class WeeklyPostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, highlight;
        CardView cardView;

        public WeeklyPostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.coverImage);
            title = itemView.findViewById(R.id.Title);
            highlight = itemView.findViewById(R.id.Highlight);
            cardView = itemView.findViewById(R.id.mainCard);

        }
    }
}
