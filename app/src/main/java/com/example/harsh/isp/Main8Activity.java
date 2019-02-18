package com.example.harsh.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main8Activity extends AppCompatActivity {
EditText data , speed , cost ;
Button submit ;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        speed = findViewById(R.id.editText);
        cost = findViewById(R.id.editText2);
        data = findViewById(R.id.editText3);
        submit = findViewById(R.id.button5);
        update();
    }
    void update()
    {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                String name ;
                name = speed.getText().toString()+"MBPS_"+data.getText().toString()+"GB";
                mDatabase.child("plans").child(name).child("cost").setValue(cost.getText().toString());
                mDatabase.child("plans").child(name).child("data").setValue(data.getText().toString());
                mDatabase.child("plans").child(name).child("speed").setValue(speed.getText().toString());
                startActivity(new Intent(Main8Activity.this,Main9Activity.class));
            }
        });
    }
}
