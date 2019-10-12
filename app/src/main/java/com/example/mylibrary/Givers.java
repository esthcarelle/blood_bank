package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Givers extends AppCompatActivity {
    private TextView mBloodTextView;
    private ListView mListView;
    private String[] giversA = new String[] {"James", "Kate",
            "Kylie", "Jenner", "Luc Lac", "Sweet Bae",
            "Wil Biz", "Rachel", "Grace", "Alice",
            "Raissa", "Flower", "Kimba",
            "Eliane", "Nicole"};
    private String[] giversB=new String[] {"odette","diane","mimi","grace","cates","Ange","blandine","benedicte","flower","lubega"};
    private String[] giversC=new String[] {"Carrelle","Peter","Paul","Mary","sylvie","rachel","Timothee","philippe","matthew","nicole","love","confiance"};
@Override
protected void onCreate(Bundle savedInstanceState) {


    super.onCreate(savedInstanceState);
    setContentView(R.layout.givers);
    Intent intent = getIntent();
    mBloodTextView = (TextView) findViewById(R.id.bloodTextView);
    String location = intent.getStringExtra("location");
    String name = intent.getStringExtra("name");
    String blood = intent.getStringExtra("blood");
    System.out.println(blood);
    mBloodTextView.setText("Here are people with blood type : " + blood);
    mListView = (ListView) findViewById(R.id.listView);
    if(blood.equals("A")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversA);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("B")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversB);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("A+")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversC);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("B+")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversA);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("AB")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversB);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("AB+")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversC);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("O+")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversA);
        mListView.setAdapter(adapter);
    }
    else if(blood.equals("O-")){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, giversB);
        mListView.setAdapter(adapter);
    }
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String giver = ((TextView)view).getText().toString();
            Toast.makeText(Givers.this, giver, Toast.LENGTH_LONG).show();
        }
    });





}


}
