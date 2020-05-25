package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.project.SecondFragment.EXTRA_CATEGORY;
import static com.example.project.SecondFragment.EXTRA_COMPANY;
import static com.example.project.SecondFragment.EXTRA_COST;
import static com.example.project.SecondFragment.EXTRA_LOCATION;
import static com.example.project.SecondFragment.EXTRA_NAME;
import static com.example.project.SecondFragment.EXTRA_SIZE;
import static com.example.project.SecondFragment.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String company = intent.getStringExtra(EXTRA_COMPANY);
        int location = intent.getIntExtra(EXTRA_LOCATION, 0);
        String category = intent.getStringExtra(EXTRA_CATEGORY);
        int size = intent.getIntExtra(EXTRA_SIZE, 0);
        int cost = intent.getIntExtra(EXTRA_COST,0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textView = findViewById(R.id.text_namn_detail);
        TextView textView1 = findViewById(R.id.text_company_detail);
        TextView textView2 = findViewById(R.id.text_location_detail);
        TextView textView3 = findViewById(R.id.text_category_detail);
        TextView textView4 = findViewById(R.id.text_size_detail);
        TextView textView5 = findViewById(R.id.text_cost_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textView.setText(name);
        textView1.setText("Upptäckt: "+company);
        textView2.setText("Avstånd från solen: "+location+"miljoner km");
        textView3.setText("Tillhör: "+category);
        textView4.setText("Diameter: "+size+"km");
        textView5.setText("Omloppstid: "+cost+" dygn");
    }
}
