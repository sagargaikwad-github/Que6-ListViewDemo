package com.example.listviewarrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.net.URLEncoder;

public class FruitDetails extends AppCompatActivity {
    TextView name, description, link;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_fruit_details);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);
        link = findViewById(R.id.link);

        Bundle bundle = getIntent().getExtras();
        String fruit_name = bundle.getString("Name", "");
        int fruit_image = bundle.getInt("Image");
        String fruit_description = bundle.getString("Desc", "");
        String fruit_link = bundle.getString("Email", "");

        name.setText(fruit_name);
        image.setImageResource(fruit_image);
        description.setText(fruit_description);
        link.setText("Read More...");

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(fruit_link));
                startActivity(intent);
            }
        });


    }
}