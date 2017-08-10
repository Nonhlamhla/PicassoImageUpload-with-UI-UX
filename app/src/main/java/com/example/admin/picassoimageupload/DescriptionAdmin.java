package com.example.admin.picassoimageupload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DescriptionAdmin extends AppCompatActivity {

    EditText editTextAddress, editTextHours, editTextPhone, editTextDescription ;
    RatingBar ratingBar2;
    ImageButton ImageButtonDescription;
    Button buttonadd;


    Uri uri;

    //IMAGE
    private static final int GALLERY_INTENT = 1;


    private DatabaseReference databaseDescription;
    private StorageReference mStorageReference;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_admin);

        databaseDescription = FirebaseDatabase.getInstance().getReference("restaurants");
        mStorageReference = FirebaseStorage.getInstance().getReference();

        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextHours = (EditText) findViewById(R.id.editTextHours);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ImageButtonDescription = ( ImageButton) findViewById(R.id.ImageButtonDescription);
        buttonadd = (Button ) findViewById(R.id.buttonadd);

        mDialog = new ProgressDialog(this);

        ImageButtonDescription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //IMAGE
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddDescription();


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            uri = data.getData();
            ImageButtonDescription.setImageURI(uri);

        }
    }
    private void AddDescription() {
        final String address = editTextAddress.getText().toString().trim();
        final String hours = editTextHours.getText().toString().trim();
        final String phone =   editTextPhone.getText().toString().trim();
        final String description = editTextDescription.getText().toString().trim();

        if (!TextUtils.isEmpty(address)) {

            mDialog.setMessage("please wait ...");
            mDialog.show();


            Log.i("T",uri.toString());
            StorageReference filePath = mStorageReference.child("restaurants").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri imageUri = taskSnapshot.getDownloadUrl();

                    //CREATES A NEW UNIQUE ID IN DATABASE
                    String id = databaseDescription.push().getKey();
                    Restaurant restaurant = new Restaurant( hours, phone,  imageUri.toString(), address);
                    databaseDescription.child(id).setValue(restaurant);
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Done Uploading ...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), DescriptionActivity.class);
                    startActivity(intent);

                }
            });
        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }
}