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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NightlifeAdmin extends AppCompatActivity {

    EditText editTextname, editTextlocation ;
    Button buttonadd;
    ImageView imageViewUrl;
    Uri uri;

    //IMAGE
    private static final int GALLERY_INTENT = 1;

    ImageButton mImage;
    private DatabaseReference databaseHistory;
    private StorageReference mStorageReference;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightlife_admin);

        databaseHistory = FirebaseDatabase.getInstance().getReference("nightlife");
        mStorageReference = FirebaseStorage.getInstance().getReference();

        mImage = (ImageButton) findViewById(R.id.ImageButton);

        editTextname = (EditText) findViewById(R.id.editTextname);
        editTextlocation = (EditText) findViewById(R.id.editTextlocation);
        buttonadd = (Button) findViewById(R.id.buttonadd);

        mDialog = new ProgressDialog(this);


        mImage.setOnClickListener(new View.OnClickListener() {

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

                AddHistory();

//                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
//                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            uri = data.getData();
            mImage.setImageURI(uri);

        }
    }


    private void AddHistory() {
        final String name = editTextname.getText().toString().trim();
        final String location = editTextlocation.getText().toString().trim();


        if (!TextUtils.isEmpty(name)) {

            mDialog.setMessage("please wait ...");
            mDialog.show();


            Log.i("T",uri.toString());
            StorageReference filePath = mStorageReference.child("NightlifeImages").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri imageUri = taskSnapshot.getDownloadUrl();


                    //CREATES A NEW UNIQUE ID IN DATABASE
                    String id = databaseHistory.push().getKey();
                    Restaurant restaurant = new Restaurant(id, name, imageUri.toString(), location);
                    databaseHistory.child(id).setValue(restaurant);
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Done Uploading ...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), NightlifeActivity.class);
                    startActivity(intent);

                }
            });
        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }
}




