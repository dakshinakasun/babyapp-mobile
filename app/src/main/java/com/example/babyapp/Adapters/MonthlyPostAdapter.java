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
import com.example.babyapp.Pages.MonthlyPosts;
import com.example.babyapp.R;

import java.util.List;

public class MonthlyPostAdapter extends RecyclerView.Adapter<MonthlyPostAdapter.MonthlyPostHolder> {

    private Context context;
    private List<MonthlyPosts> monthlyPostsList;

    public MonthlyPostAdapter(Context context, List<MonthlyPosts> monthlyPosts) {
        this.context = context;
        monthlyPostsList = monthlyPosts;
    }

    @NonNull
    @Override
    public MonthlyPostAdapter.MonthlyPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent ,false);
        return new MonthlyPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyPostAdapter.MonthlyPostHolder holder, int position) {

        MonthlyPosts monthlyPosts = monthlyPostsList.get(position);
        holder.title.setText(monthlyPosts.getTitle());
        holder.highlight.setText(monthlyPosts.getHighlight());
        Glide.with(context).load(monthlyPosts.getImage()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title", monthlyPosts.getTitle());
                bundle.putString("sititle", monthlyPosts.getSiTitle());
                bundle.putString("description", monthlyPosts.getDescription());
                bundle.putString("sidescription", monthlyPosts.getSiDescription());
                bundle.putString("image", monthlyPosts.getImage());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return monthlyPostsList.size();
    }

    public class MonthlyPostHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, highlight;
        CardView cardView;

        public MonthlyPostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.coverImage);
            title = itemView.findViewById(R.id.Title);
            highlight = itemView.findViewById(R.id.Highlight);
            cardView = itemView.findViewById(R.id.mainCard);

        }
    }
}
