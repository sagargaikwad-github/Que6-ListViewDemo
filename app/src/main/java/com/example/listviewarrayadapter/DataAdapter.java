package com.example.listviewarrayadapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<FruitData> {
    public DataAdapter(@NonNull Context context, ArrayList<FruitData> arrayList) {
        super(context,0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

           // convertView=LayoutInflater.from(getContext()).inflate(R.layout.list_display,parent,false);

            FruitData fruitData=getItem(position);

            if(convertView==null)
            {
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.list_display,parent,false);
            }

            ImageView imageView=convertView.findViewById(R.id.imageView);
            imageView.setImageResource(fruitData.getImage());

            TextView textView=convertView.findViewById(R.id.textView2);
            textView.setText(fruitData.getName());

            TextView textView1=convertView.findViewById(R.id.textView3);
            textView1.setText(fruitData.getShort_desc());


        return convertView;
    }
}

