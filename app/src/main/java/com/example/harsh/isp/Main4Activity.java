package com.example.harsh.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
Spinner plan;
Button submit ;
String selected_plan,accno;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
        plan = findViewById(R.id.spinner);
        submit = findViewById(R.id.button4);
        Bundle b = getIntent().getExtras();
        if(b!= null)
        {
            accno = b.getString("accno");
        }
        final List<String> list = new ArrayList<String>();
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        plan.setAdapter(dataAdapter);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("plans");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                list.add(dataSnapshot.getKey().toString());
                dataAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getKey().toString());
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        });

        plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_plan = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Main4Activity.this, "PLEASE SELECT PLAN", Toast.LENGTH_SHORT).show();
            }
        });
     submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("users").child(accno).child("plan").setValue(selected_plan);
            Intent i3 = new Intent(Main4Activity.this,Main2Activity.class);
            i3.putExtra("plan",selected_plan);
            startActivity(i3);
         }
     });
    }

}

