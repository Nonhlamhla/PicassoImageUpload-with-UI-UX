package com.example.admin.picassoimageupload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NightlifeActivity extends AppCompatActivity {
    ListView ListViewNightlife;
    List<Restaurant> restaurantList;

    DatabaseReference databaseRestaurants;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightlife);

        databaseRestaurants = FirebaseDatabase.getInstance().getReference("nightlife");
        ListViewNightlife = (ListView)findViewById(R.id.ListViewNightlife);

        restaurantList = new ArrayList<>();




        databaseRestaurants.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                restaurantList.clear();
                for (DataSnapshot restaurantSnapshot : dataSnapshot.getChildren()){
                    Restaurant restaurant = restaurantSnapshot.getValue(Restaurant.class);
                    restaurantList.add(restaurant);
                }
                RestaurantList adapter = new RestaurantList(NightlifeActivity.this,restaurantList);
                ListViewNightlife.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
