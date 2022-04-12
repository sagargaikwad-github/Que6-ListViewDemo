package com.example.listviewarrayadapter;





import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FruitDetails2 extends AppCompatActivity {

    ImageView img;
    TextView name,desc;
    Button save;
    ArrayList<FruitData> arrayList ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        img = findViewById(R.id.image2);
        name = findViewById(R.id.name2);
        desc = findViewById(R.id.description2);
        save = findViewById(R.id.save);


        Bundle bundle = getIntent().getExtras();
        int a = bundle.getInt("Image");
        String b = bundle.getString("Name", "");
        String c = bundle.getString("Desc", "");
        // int d=bundle.getInt("Position");


        getdata();
        arrayList=getdata();

        img.setImageResource(a);
        name.setText(b);
        desc.setText(c);

        //int e=arrayList.indexOf(d);
        //Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
        int d = bundle.getInt("Position");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x=name.getText().toString();
                String y=desc.getText().toString();
                arrayList.set(d,new FruitData(0,a,x,"SHORT",y,"EMAIL"));

                savedata(arrayList,"");

                Toast.makeText(FruitDetails2.this, "Data Added", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void savedata(ArrayList<FruitData> list,String key) {
        SharedPreferences sharedPreferences=getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(list);
        editor.putString("Task",json);
        editor.apply();
    }

    public ArrayList<FruitData> getdata()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Save", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Task","");
        Type type = new TypeToken<ArrayList<FruitData>>() {}.getType();
        arrayList=gson.fromJson(json, type);
        return arrayList;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(FruitDetails2.this,MainActivity.class);
        startActivity(intent);
        super.onRestart();
    }


}


