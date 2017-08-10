package com.example.admin.picassoimageupload;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DescriptionActivity extends AppCompatActivity {

    TextView textView, TextViewtAddress, TextViewHours, TextViewPhone,rating, TextViewDescription ;
    RatingBar ratingBar2;
    ImageButton ImageButtonDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        textView = (TextView)findViewById(R.id.textView);
        TextViewtAddress = (TextView)findViewById(R.id. TextViewtAddress);
        TextViewHours = (TextView)findViewById(R.id.TextViewHours);
        TextViewPhone = (TextView)findViewById(R.id.TextViewPhone);
        rating = (TextView)findViewById(R.id.textView);
        TextViewDescription = (TextView)findViewById(R.id.TextViewDescription);
        ratingBar2 = (RatingBar)findViewById(R.id.ratingBar2);
        ImageButtonDescription = ( ImageButton)findViewById(R.id.ImageButtonDescription);




        Intent i = getIntent();
        Restaurant restaurant = (Restaurant) i.getSerializableExtra("select");

        TextViewtAddress.setText(restaurant.getRestaurantname());
        TextViewHours.setText(restaurant.getRestaurantlocation());
        Glide.with(this)
                .load(restaurant.getRestaurantimageurl())
                .into(ImageButtonDescription);


    }
}
