package com.example.admin.picassoimageupload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView ListViewHistory;
    List<Restaurant> restaurantList;

    DatabaseReference databaseRestaurants;

//    //ADDED
//    public  static Restaurant SELECTED_ITEM;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        databaseRestaurants = FirebaseDatabase.getInstance().getReference("history");
        ListViewHistory = (ListView)findViewById(R.id.ListViewHistory);

        restaurantList = new ArrayList<>();

//        //ADDED
//        ListViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(HistoryActivity.this,DescriptionActivity.class);
//
//               Restaurant restaurant = (Restaurant)  ListViewHistory.getItemAtPosition(i);
//                SELECTED_ITEM =restaurant;
//                intent.putExtra("select", SELECTED_ITEM);
//                startActivity(intent);
//
//
//            }
//        });



        databaseRestaurants.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                restaurantList.clear();
                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()){
                    Restaurant restaurant = restaurantSnapshot.getValue(Restaurant.class);
                    restaurantList.add(restaurant);
                }
                RestaurantList adapter = new RestaurantList(HistoryActivity.this,restaurantList);
                ListViewHistory.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
