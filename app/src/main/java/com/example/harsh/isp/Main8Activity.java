package com.example.harsh.isp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main8Activity extends AppCompatActivity {
EditText data , speed , cost ;
Button submit ;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        speed = findViewById(R.id.editText);
        speed.setFilters(new InputFilter[]{new InputFilterMinMax(1, 10000)});
        cost = findViewById(R.id.editText2);
        cost.setFilters(new InputFilter[]{new InputFilterMinMax(1, 5000)});
        data = findViewById(R.id.editText3);
        data.setFilters(new InputFilter[]{new InputFilterMinMax(1, 1000)});
        submit = findViewById(R.id.button5);
        update();
    }
    void update()
    {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                final String name;
                if (Integer.parseInt(speed.getText().toString()) > 1000) {
                    speed.setText(Integer.parseInt(speed.getText().toString()) / 1000);
                    name = speed.getText().toString() + "GBPS_" + data.getText().toString() + "GB";
                    AlertDialog.Builder alert = new AlertDialog.Builder(Main8Activity.this);
                    alert.setTitle("SPEED UPDATION");
                    alert.setMessage("Speed unit has been changed to GBPS ");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });


                    alert.show();

                } else
                    name = speed.getText().toString() + "MBPS_" + data.getText().toString() + "GB";

                mDatabase.child("plans").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(name)) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(Main8Activity.this);
                            alert.setTitle("PLAN WITH SAME CONFIGURATION EXISTS");
                            alert.setMessage("Click Cancel to go back\n Ok to change the plan");
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    AlertDialog.Builder alert = new AlertDialog.Builder(Main8Activity.this);
                                    alert.setTitle("PLAN UPDATION");
                                    alert.setMessage("You are about to update an existing plan ");
                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mDatabase.child("plans").child(name).child("cost").setValue(cost.getText().toString());
                                            mDatabase.child("plans").child(name).child("data").setValue(data.getText().toString());
                                            mDatabase.child("plans").child(name).child("speed").setValue(speed.getText().toString());
                                            startActivity(new Intent(Main8Activity.this, Main9Activity.class));
                                        }
                                    });
                                    alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    alert.show();

                                }
                            });
                            alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alert.show();

                        } else {

                            mDatabase.child("plans").child(name).child("cost").setValue(cost.getText().toString());
                            mDatabase.child("plans").child(name).child("data").setValue(data.getText().toString());
                            mDatabase.child("plans").child(name).child("speed").setValue(speed.getText().toString());
                            startActivity(new Intent(Main8Activity.this, Main9Activity.class));

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });
    }
}
