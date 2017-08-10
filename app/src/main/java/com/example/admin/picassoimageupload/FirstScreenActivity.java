package com.example.admin.picassoimageupload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FirstScreenActivity extends AppCompatActivity {


    private Button StartTour;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen_activity);

        StartTour = (Button) findViewById(R.id.StartTour);
        imageView = (ImageView)findViewById(R.id.imageView);

//        //GLIDE
//        String url = "https://firebasestorage.googleapis.com/v0/b/picassoimageupload-4fcc2.appspot.com/o/view0.png?alt=media&token=f1e57782-9119-43fd-a76a-fbc3009d340f";
//        Glide.with(getApplicationContext()).load(url).into(imageView);

        StartTour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondScreenActivity.class);
                startActivity(intent);

            }
        });
    }

}
