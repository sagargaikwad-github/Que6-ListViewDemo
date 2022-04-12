package com.example.listviewarrayadapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MyAdapterRecyclerView extends RecyclerView.Adapter<MyAdapterRecyclerView.holder>{
  ArrayList<FruitData> arrayList;
  Context context;
  BottomSheetDialog sheetDialog;
    ArrayList<FruitData> a2 = new ArrayList<>();
  private MainActivity mainActivity;

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
    public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {
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
//                Intent intent=new Intent(context,FruitDetails.class);
//                intent.putExtra("Name",temp.getName());
//                intent.putExtra("Image",temp.getImage());
//                intent.putExtra("Email",temp.getEmail());
//                intent.putExtra("Desc",temp.getDesc());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                 showBottomDialog();

                sheetDialog=new BottomSheetDialog(context,R.style.BottomSheetTheme);
                LinearLayout sheetLayout=sheetDialog.findViewById(R.id.bottom);

//            LinearLayout update=sheetDialog.findViewById(R.id.update);
//            LinearLayout delete=sheetDialog.findViewById(R.id.delete);


//                [] view=LayoutInflater.from(context).inflate(R.layout.activity_bottom_sheet_dialog,
//                        sheetLayout);

                view=LayoutInflater.from(context).inflate(R.layout.bottom_sheet,
                        sheetLayout);

                sheetDialog.setContentView(view);
                sheetDialog.show();

                final LinearLayout update=view.findViewById(R.id.update1);
                final LinearLayout delete=view.findViewById(R.id.delete1);
                final LinearLayout viewbutton=view.findViewById(R.id.view1);


               viewbutton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent=new Intent(context,FruitDetails.class);
                       intent.putExtra("Name",temp.getName());
                       intent.putExtra("Image",temp.getImage());
                       intent.putExtra("Email",temp.getEmail());
                       intent.putExtra("Desc",temp.getDesc());
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       context.startActivity(intent);
                       sheetDialog.dismiss();
                   }
               });

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                Intent intent=new Intent(context,FruitDetails2.class);
                intent.putExtra("Position",holder.getAdapterPosition());
                intent.putExtra("Name",temp.getName());
                intent.putExtra("ID",temp.getId());
                intent.putExtra("Image",temp.getImage());
                intent.putExtra("Email",temp.getEmail());
                intent.putExtra("Desc",temp.getDesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                sheetDialog.dismiss();
                
                ((MainActivity)context).finish();
                    }
                });


                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        arrayList.remove(holder.getAdapterPosition());
//                        Toast.makeText(context, "Fruit Deleted Sucessfully", Toast.LENGTH_LONG).show();
//                        notifyDataSetChanged();
//                        savedata(arrayList,"");
//                        sheetDialog.dismiss();

                        a2=getdata();
                       for(int i=0;i<a2.size();i++)
                       {
                           if(a2.get(i).getId()==temp.getId())
                           {
                              a2.remove(i);
                           }
                       }
//                        a2.remove(a);
//                        Toast.makeText(context, String.valueOf(a), Toast.LENGTH_SHORT).show();

                        savedata(a2,"");
                        notifyDataSetChanged();
                        sheetDialog.dismiss();

                    }

//                    private void deleteItem() {
//                       //FruitData fn=a2.get(position);
//                        final FruitData FD=a2.get(position);
//                        for(int a=1;a<=7;a++)
//                        {
//                           a2.remove(a2.get(FD.getId()));
//                            //Toast.makeText(context, String.valueOf(a), Toast.LENGTH_SHORT).show();
//                        }
//                        savedata(a2,"");
//
//                    }






                });
            }

        });

    }




//    private void showBottomDialog() {
//            sheetDialog=new BottomSheetDialog(context,R.style.BottomSheetTheme);
//            LinearLayout sheetLayout=sheetDialog.findViewById(R.id.Lay);
//
////            LinearLayout update=sheetDialog.findViewById(R.id.update);
////            LinearLayout delete=sheetDialog.findViewById(R.id.delete);
//
//            View view=LayoutInflater.from(context).inflate(R.layout.activity_bottom_sheet_dialog,
//                    sheetLayout);
//
//            sheetDialog.setContentView(view);
//            sheetDialog.show();
//
//            final LinearLayout update=view.findViewById(R.id.update);
//            final LinearLayout delete=view.findViewById(R.id.delete);
//
//
//
//
//
//    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterList(ArrayList<FruitData> filterllist) {
        arrayList = filterllist;
        notifyDataSetChanged();
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
    private void savedata(ArrayList<FruitData> list, String key) {
        SharedPreferences sharedPreferences= context.getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(list);
        editor.putString("Task",json);
        editor.apply();
    }
    public ArrayList<FruitData> getdata()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Save", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Task","");
        Type type = new TypeToken<ArrayList<FruitData>>() {}.getType();
        arrayList=gson.fromJson(json, type);
        return arrayList;
    }



}
