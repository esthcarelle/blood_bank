package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Informations extends AppCompatActivity {
    private Button mAddInfos;
    private EditText editName;
    private EditText editLocation;
    private Spinner editBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.informations);
        mAddInfos=(Button) findViewById(R.id.addInfos);
        editBlood=(Spinner) findViewById(R.id.spinner1) ;
        editLocation=(EditText) findViewById(R.id.locationEditText) ;
        editName=(EditText) findViewById(R.id.nameEditText) ;

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
                Intent intent = new Intent(Informations.this, Givers.class);
                intent.putExtra("location", location);
                intent.putExtra("name", name);
                intent.putExtra("blood", blood);

                startActivity(intent);
            }


        });


    }


}
