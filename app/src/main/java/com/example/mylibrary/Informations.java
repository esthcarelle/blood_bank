package com.example.mylibrary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Informations extends AppCompatActivity {
    private Button mAddInfos;
    private EditText editName;
    private EditText editLocation;
    private Spinner editBlood;

    private DatabaseReference mNewInfos;
    private Button mSavedRestaurantsButton;
    private Button uploadImage;
    private ImageView nImageView;
    public static final int CAMERA_REQUEST_CODE= 1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.informations);


        mNewInfos= FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_NEW_INFOS);

        mAddInfos=(Button) findViewById(R.id.addInfos);
        editBlood=(Spinner) findViewById(R.id.spinner1) ;
        editLocation=(EditText) findViewById(R.id.locationEditText) ;
        editName=(EditText) findViewById(R.id.nameEditText) ;
       uploadImage=(Button) findViewById(R.id.takePicture);
        nImageView=(ImageView) findViewById(R.id.picture);
        mStorage= FirebaseStorage.getInstance().getReference();
        mProgress=new ProgressDialog(this);


        String[] items = new String[]{"A", "B", "A+","B+","AB","AB+","O+","O-"};
        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        mAddInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String location=editLocation.getText().toString();
                String blood=editBlood.getSelectedItem().toString();
                SAveInformations newInfos=new SAveInformations(name,location,blood);
                Intent intent = new Intent(Informations.this, Givers.class);
                saveInfosToFirebase(newInfos);
                intent.putExtra("location", location);
                intent.putExtra("name", name);
                intent.putExtra("blood", blood);

                startActivity(intent);
            }


        });
        mSavedRestaurantsButton=(Button) findViewById(R.id.savedButton);
        mSavedRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Informations.this, SavedCentersListActivity.class);
                startActivity(intent);

            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK){

            mProgress.setMessage("uploadinggggggg");
            mProgress.show();
            Uri uri=data.getData();
            StorageReference filepath=mStorage.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mProgress.dismiss();

                    Toast.makeText(Informations.this,"Upload finished",Toast.LENGTH_LONG).show();

                }
            });
        }
    }



    public void saveInfosToFirebase(SAveInformations infos) {
        mNewInfos.setValue(infos);
    }



}
