package com.example.babyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SiDetailActivity extends AppCompatActivity {

    String mImage;
    String SiTitle;
    String SiDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_si_detail);

        ImageView enImage = findViewById(R.id.imageView4);
        TextView english = findViewById(R.id.tvEnglish);

        ImageView imageView = findViewById(R.id.imageView);
        TextView title = findViewById(R.id.detail_title);
        TextView description = findViewById(R.id.detail_desc);

        Intent intent = getIntent();
        mImage = intent.getStringExtra("mImage");
        SiTitle = intent.getStringExtra("SiTitle");
        SiDescription = intent.getStringExtra("SiDescription");

        Glide.with(this).load(mImage).into(imageView);
        title.setText(SiTitle.toString());
        description.setText(Html.fromHtml(SiDescription).toString());

        enImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}