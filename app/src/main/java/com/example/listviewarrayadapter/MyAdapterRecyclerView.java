package com.example.listviewarrayadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterRecyclerView extends RecyclerView.Adapter<MyAdapterRecyclerView.holder> {
  ArrayList<FruitData> arrayList;
  Context context;

    public MyAdapterRecyclerView(ArrayList<FruitData> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_row,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final FruitData temp=arrayList.get(position);
        holder.r_img.setImageResource(arrayList.get(position).getImage());
        holder.r_name.setText(arrayList.get(position).getName());

//        FruitData obj=arrayList.get(position);
//        String text=obj.getName();
//        int img=obj.getImage();
//
//        holder.r_name.setText(text);
//        holder.r_img.setImageResource(img);

        holder.r_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,FruitDetails.class);
                intent.putExtra("Name",temp.getName());
                intent.putExtra("Image",temp.getImage());
                intent.putExtra("Email",temp.getEmail());
                intent.putExtra("Desc",temp.getDesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class holder extends RecyclerView.ViewHolder {
        TextView r_name;
        ImageView r_img;
        public holder(@NonNull View itemView) {
            super(itemView);
            r_name=itemView.findViewById(R.id.rec_name);
            r_img=itemView.findViewById(R.id.rec_img);
        }


    }


}
