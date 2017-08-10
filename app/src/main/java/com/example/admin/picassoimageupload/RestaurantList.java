package com.example.admin.picassoimageupload;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * RESTAURANT ADAPTER
 */

public class RestaurantList extends ArrayAdapter<Restaurant> {

    private Activity context;
    private List<Restaurant> restaurantList;
    private Activity applicationContext;

    public RestaurantList(Activity context, List<Restaurant> restaurantList) {
        super(context, R.layout.list_layout, restaurantList);
        this.context = context;
        this.restaurantList = restaurantList;


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewLocation = (TextView) listViewItem.findViewById(R.id.textViewLocation);
        ImageView imageViewUrl = (ImageView) listViewItem.findViewById(R.id.imageViewUrl);
        ImageButton ImageButtonView = (ImageButton) listViewItem.findViewById(R.id.ImageButtonView);
        ImageButton ImageButtonShare = (ImageButton) listViewItem.findViewById(R.id.ImageButtonShare);

        Restaurant restaurant = restaurantList.get(position);
        textViewName.setText(restaurant.getRestaurantname());
        textViewLocation.setText(restaurant.getRestaurantlocation());
        //textViewLocation.setText(restaurant.getRestaurantimageurl());



            Glide.with(context)
                    .load(restaurant.getRestaurantimageurl())
                    .into(imageViewUrl);


        ImageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "So/WhEre/TO Tour Guide App");
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        ImageButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,DescriptionActivity.class);
                context.startActivity(intent);


            }
        });


            return listViewItem;
        }


}
