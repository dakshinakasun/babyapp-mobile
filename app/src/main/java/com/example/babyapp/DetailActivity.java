package com.example.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView sinhala = findViewById(R.id.tvSinhala);
        ImageView siImage = findViewById(R.id.imageView3);

        ImageView imageView = findViewById(R.id.imageView);
        TextView title = findViewById(R.id.detail_title);
        TextView description = findViewById(R.id.detail_desc);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String SiTitle = bundle.getString("sititle");
        String mDescription = bundle.getString("description");
        String SiDescription = bundle.getString("sidescription");
        String mImage = bundle.getString("image");

        Glide.with(this).load(mImage).into(imageView);
        title.setText(mTitle.toString());
        description.setText(Html.fromHtml(mDescription).toString());

        siImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this, SiDetailActivity.class);
                intent.putExtra("mImage", mImage);
                intent.putExtra("SiTitle", SiTitle);
                intent.putExtra("SiDescription", SiDescription);
                startActivity(intent);
            }
        });

        sinhala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailActivity.this, SiDetailActivity.class);
                intent.putExtra("mImage", mImage);
                intent.putExtra("SiTitle", SiTitle);
                intent.putExtra("SiDescription", SiDescription);
                startActivity(intent);
            }
        });

    }
}