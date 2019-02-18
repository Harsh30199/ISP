package com.example.harsh.isp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main9Activity extends AppCompatActivity {

    Button add, delete;
    DatabaseReference dref;
    ListView plans;
    ArrayList<String> list = new ArrayList<>();
    String speed, cost, data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        plans = findViewById(R.id.planlist);
        add = findViewById(R.id.button6);
        delete = findViewById(R.id.button7);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main9Activity.this, Main8Activity.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main9Activity.this, Main10Activity.class));
            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        plans.setAdapter(adapter);
        dref = FirebaseDatabase.getInstance().getReference().child("plans");
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                list.add(dataSnapshot.getKey().toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getKey().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        plans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference mDatabase;

                final String name;

                name = parent.getItemAtPosition(position).toString();

                mDatabase = FirebaseDatabase.getInstance().getReference().child("plans").child(name);
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        speed = dataSnapshot.child("speed").getValue().toString();

                        cost = dataSnapshot.child("cost").getValue().toString();

                        data = dataSnapshot.child("data").getValue().toString();
                        AlertDialog.Builder alert = new AlertDialog.Builder(Main9Activity.this);
                        alert.setTitle("Plan Details");
                        alert.setMessage("SPEED : " + speed + " MBPS \nCOST   : RS." + cost + "\nDATA   : " + data + " GB");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        alert.show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}