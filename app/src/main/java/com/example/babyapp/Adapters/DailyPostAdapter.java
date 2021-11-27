package com.example.babyapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.babyapp.Pages.DailyPosts;
import com.example.babyapp.R;

import java.util.List;

public class DailyPostAdapter extends RecyclerView.Adapter<DailyPostAdapter.DailyPostHolder> {

    private Context context;
    private List<DailyPosts> dailyPostList;

    public DailyPostAdapter(Context context, List<DailyPosts> dailyPosts) {
        this.context = context;
        dailyPostList = dailyPosts;
    }

    @NonNull
    @Override
    public DailyPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent ,false);
        return new DailyPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyPostHolder holder, int position) {

        DailyPosts dailyPosts = dailyPostList.get(position);
        holder.title.setText(dailyPosts.getTitle());
        holder.highlight.setText(dailyPosts.getHighlight());
        Glide.with(context).load(dailyPosts.getImage()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", dailyPosts.getTitle());
                bundle.putString("sititle", dailyPosts.getSiTitle());
                bundle.putString("description", dailyPosts.getDescription());
                bundle.putString("sidescription", dailyPosts.getSiDescription());
                bundle.putString("image", dailyPosts.getImage());

                intent.putExtras(bundle);
                context.startActivity(intent);

                holder.cardView.setCardBackgroundColor(Color.parseColor("#FFFAFA"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dailyPostList.size();
    }

    public class DailyPostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, highlight;
        CardView cardView;

        public DailyPostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.coverImage);
            title = itemView.findViewById(R.id.Title);
            highlight = itemView.findViewById(R.id.Highlight);
            cardView = itemView.findViewById(R.id.mainCard);

        }
    }
}
