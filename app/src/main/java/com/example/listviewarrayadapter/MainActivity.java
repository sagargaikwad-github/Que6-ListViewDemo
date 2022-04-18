package com.example.listviewarrayadapter;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import static java.util.Locale.filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // ListView lv;
    TextView nodata;
    ArrayList<FruitData> arrayList;
    String ab;
   RecyclerView recyclerView;
  MyAdapterRecyclerView myAdapterRecyclerView;
    ArrayList<FruitData> a1 = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // lv = findViewById(R.id.listview);
        nodata = findViewById(R.id.nodata);
        searchView=findViewById(R.id.search);



        ArrayList<FruitData> arrayList = new ArrayList<>();
        arrayList.add(new FruitData(0,R.drawable.apple, "Apple", "An apple a day keeps the doctor away",
                "        An apple is an edible fruit produced by an apple tree (Malus domestica). Apple trees are cultivated worldwide and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek, and European Christian tradition.\n" +
                        "    Apples grown from seed tend to be very different from those of their parents, and the resultant fruit frequently lacks desired characteristics. Generally, apple cultivars are propagated by clonal grafting onto rootstocks. Apple trees grown without rootstocks tend to be larger and much slower to fruit after planting. Rootstocks are used to control the speed of growth and the size of the resulting tree, allowing for easier harvesting.\n" +
                        "    There are more than 7,500 known cultivars of apples. Different cultivars are bred for various tastes and uses, including cooking, eating raw, and cider production. Trees and fruit are prone to a number of fungal, bacterial, and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.", "https://en.wikipedia.org/wiki/Apple"));
        arrayList.add(new FruitData(1,R.drawable.bananas, "Banana", "Goals are like bananas, they come in bunches.", "      A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, bananas used for cooking may be called \"plantains\", distinguishing them from dessert bananas. The fruit is variable in size, color, and firmness, but is usually elongated and curved, with soft flesh rich in starch covered with a rind, which may be green, yellow, red, purple, or brown when ripe. The fruits grow upward in clusters near the top of the plant. Almost all modern edible seedless (parthenocarp) bananas come from two wild species – Musa acuminata and Musa balbisiana. The scientific names of most cultivated bananas are Musa acuminata, Musa balbisiana, and Musa × paradisiaca for the hybrid Musa acuminata × M. balbisiana, depending on their genomic constitution. The old scientific name for this hybrid, Musa sapientum, is no longer used.", "https://en.wikipedia.org/wiki/Bananas"));
        arrayList.add(new FruitData(2,R.drawable.lemon, "Lemon", "When life gives you lemons, learn to juggle.", "      The lemon (Citrus limon) is a species of small evergreen trees in the flowering plant family Rutaceae, native to Asia, primarily Northeast India (Assam), Northern Myanmar or China.\n" + "    The tree's ellipsoidal yellow fruit is used for culinary and non-culinary purposes throughout the world, primarily for its juice, which has both culinary and cleaning uses.[2] The pulp and rind are also used in cooking and baking. The juice of the lemon is about 5% to 6% citric acid, with a pH of around 2.2, giving it a sour taste. The distinctive sour taste of lemon juice makes it a key ingredient in drinks and foods such as lemonade and lemon meringue pie.", "https://en.wikipedia.org/wiki/Lemon"));
        arrayList.add(new FruitData(3,R.drawable.orange, "Orange", "Orange is red brought nearer to humanity by yellow.", "      An orange is a fruit of various citrus species in the family Rutaceae (see list of plants known as orange); it primarily refers to Citrus × sinensis, which is also called sweet orange, to distinguish it from the related Citrus × aurantium, referred to as bitter orange. The sweet orange reproduces asexually (apomixis through nucellar embryony); varieties of sweet orange arise through mutations.\n" + "   The orange is a hybrid between pomelo (Citrus maxima) and mandarin (Citrus reticulata). The chloroplast genome, and therefore the maternal line, is that of pomelo. The sweet orange has had its full genome sequenced.\n" +
                "    The orange originated in a region encompassing Southern China, Northeast India, and Myanmar,and the earliest mention of the sweet orange was in Chinese literature in 314 BC. As of 1987, orange trees were found to be the most cultivated fruit tree in the world. Orange trees are widely grown in tropical and subtropical climates for their sweet fruit. The fruit of the orange tree can be eaten fresh, or processed for its juice or fragrant peel. As of 2012, sweet oranges accounted for approximately 70% of citrus production.", "https://en.wikipedia.org/wiki/Orange"));
        arrayList.add(new FruitData(4,R.drawable.strawberry, "Strawberry", "Keep calm and eat a strawberry.", "      The garden strawberry (or simply strawberry; Fragaria × ananassa) is a widely grown hybrid species of the genus Fragaria, collectively known as the strawberries, which are cultivated worldwide for their fruit. The fruit is widely appreciated for its characteristic aroma, bright red color, juicy texture, and sweetness. It is consumed in large quantities, either fresh or in such prepared foods as jam, juice, pies, ice cream, milkshakes, and chocolates. Artificial strawberry flavorings and aromas are also widely used in products such as candy, soap, lip gloss, perfume, and many others.\n" + "    The garden strawberry was first bred in Brittany, France, in the 1750s via a cross of Fragaria virginiana from eastern North America and Fragaria chiloensis, which was brought from Chile by Amédée-François Frézier in 1714. Cultivars of Fragaria × ananassa have replaced, in commercial production, the woodland strawberry (Fragaria vesca), which was the first strawberry species cultivated in the early 17th century.", "https://en.wikipedia.org/wiki/Strawberry"));

        arrayList.add(new FruitData(5,R.drawable.mango, "Mango", "Keep it cool diet with with mango bites spicy and enjoy be tight.", "      A mango is an edible stone fruit produced by the tropical tree Mangifera indica which is believed to have originated from the region between northwestern Myanmar, Bangladesh, and northeastern India. M. indica has been cultivated in South and Southeast Asia since ancient times resulting in two distinct types of modern mango cultivars: the \"Indian type\" and the \"Southeast Asian type\". Other species in the genus Mangifera also produce edible fruits that are also called \"mangoes\", the majority of which are found in the Malesian ecoregion.\n" + "     Mango trees grow to 30–40 m (98–131 ft) tall, with a crown radius of 10–15 m (33–49 ft). The trees are long-lived, as some specimens still fruit after 300 years.\n" +
                "  In deep soil, the taproot descends to a depth of 6 m (20 ft), with profuse, wide-spreading feeder roots and anchor roots penetrating deeply into the soil. The leaves are evergreen, alternate, simple, 15–35 cm (5.9–13.8 in) long, and 6–16 cm (2.4–6.3 in) broad; when the leaves are young they are orange-pink, rapidly changing to a dark, glossy red, then dark green as they mature." +
                "Worldwide, there are several hundred cultivars of mango. Depending on the cultivar, mango fruit varies in size, shape, sweetness, skin color, and flesh color which may be pale yellow, gold, green, or orange. Mango is the national fruit of India, Pakistan and the Philippines, while the mango tree is the national tree of Bangladesh", "https://en.wikipedia.org/wiki/Mango"));

        arrayList.add(new FruitData(6,R.drawable.pineapple, "Pineapple", "Be a pineapple: Stand tall, wear a crown, and be sweet on the inside.", "      The pineapple (Ananas comosus) is a tropical plant with an edible fruit; it is the most economically significant plant in the family Bromeliaceae. The pineapple is indigenous to South America, where it has been cultivated for many centuries. The introduction of the pineapple to Europe in the 17th century made it a significant cultural icon of luxury. Since the 1820s, pineapple has been commercially grown in greenhouses and many tropical plantations." +
                "\n" +
                "    Pineapples grow as a small shrub; the individual flowers of the unpollinated plant fuse to form a multiple fruit. The plant is normally propagated from the offset produced at the top of the fruit, or from a side shoot, and typically mature within a year.", "https://en.wikipedia.org/wiki/Pineapple"));

//        if (arrayList.isEmpty()) {
//            getdata();
//        }



        if (getdata() == null) {
            savedata(arrayList, "");
        }


//        SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putStringSet("ArrayList",ArrayList<>);
//        editor.apply();
//        DataAdapter adapter = new DataAdapter(this, arrayList);
//        if (adapter.isEmpty()) {
//            nodata.setVisibility(View.VISIBLE);
//        } else {
//            lv.setAdapter(adapter);
//        }
//
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String name = arrayList.get(i).getName();
//                int img = arrayList.get(i).getImage();
//                String desc = arrayList.get(i).getDesc();
//                String email = arrayList.get(i).getEmail();
//
//                Intent intent = new Intent(MainActivity.this, FruitDetails.class);
//                intent.putExtra("Name", String.valueOf(name));
//                intent.putExtra("Image", String.valueOf(img));
//                intent.putExtra("Desc", String.valueOf(desc));
//                intent.putExtra("Email", email);
//                startActivity(intent);
//
//            }
//        });


        recyclerView = findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2));

            a1 = getdata();


        myAdapterRecyclerView = new MyAdapterRecyclerView(a1, MainActivity.this);
        recyclerView.setAdapter(myAdapterRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }
           @Override
           public boolean onQueryTextChange(String s) {
               filter(s);
               return false;
           }

       });

//       searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//           @Override
//           public boolean onClose() {
//               searchView.clearFocus();
//               return false;
//           }
//       });


    }

    private void filter(String s) {
        ArrayList<FruitData> filteredlist = new ArrayList<>();
        for (FruitData item : getdata())
        //we changed here arraylist to getdata() beacuse when we clear a searchview it gets a data from a arraylist
            //   we need to get data from deleted list so getdata is a sharedpreference that is updated new data.
        {
            if (item.getName().toLowerCase().contains(s.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            nodata.setVisibility(View.VISIBLE);
            searchView.clearFocus();
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            myAdapterRecyclerView.filterList(filteredlist);
            nodata.setVisibility(View.GONE);
            myAdapterRecyclerView.notifyDataSetChanged();

        }

    }


    private void savedata(ArrayList<FruitData> list, String key) {
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
        finish();
    }

}