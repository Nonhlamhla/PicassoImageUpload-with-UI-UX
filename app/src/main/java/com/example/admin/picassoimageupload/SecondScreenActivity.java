package com.example.admin.picassoimageupload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SecondScreenActivity extends AppCompatActivity {

    private ImageButton restaurants;
    private ImageButton history;
    private ImageButton excursions;
    private ImageButton accommodation;
    private ImageButton nightlife;
    private ImageButton art;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen_activity);

        restaurants = (ImageButton) findViewById(R.id.restaurants);
        history = (ImageButton) findViewById(R.id.history);
        excursions = (ImageButton) findViewById(R.id.excursions);
        accommodation= (ImageButton) findViewById(R.id.accommodation);
        nightlife = (ImageButton) findViewById(R.id.nightlife);
        art = (ImageButton) findViewById(R.id.art);

        restaurants.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),   Main2Activity.class); //MainActivity, Main2Activity
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class); //HistoryAdmin, Main2Activity
                startActivity(intent);
            }
        });

        excursions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExcursionsActivity.class); //ExcursionsAdmin, ExcursionsActivity
                startActivity(intent);
            }
        });

        accommodation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AccommodationActivity.class); //AccommodationAdmin, AccommodationActivity
                startActivity(intent);
            }
        });

        nightlife.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NightlifeActivity.class); //NightlifeAdmin, NightlifeActivity
                startActivity(intent);
            }
        });

        art.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ArtActivity.class); //ArtAdmin, ArtActivity
                startActivity(intent);
            }
        });
    }
}



