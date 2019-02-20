package com.example.harsh.isp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
Button pay ;
Button change ;
TextView plan ;
    String accno, cost, data, speed, plan2;
int i = 0 ;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        pay = findViewById(R.id.button2);
        change = findViewById(R.id.button);
        plan = findViewById(R.id.textView2);
        Bundle b = getIntent().getExtras();
        if (b != null)
        {       plan.setText(b.getString("plan"));
                accno = b.getString("accno");
            plan2 = b.getString("plan");
        } else {
            plan.setText(plan2);
        }
       changebutton();
       paybutton();
        planviewer();
    }

    void planviewer() {
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase;

                final String name;

                name = plan.getText().toString();

                mDatabase = FirebaseDatabase.getInstance().getReference().child("plans").child(name);
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        speed = dataSnapshot.child("speed").getValue().toString();

                        cost = dataSnapshot.child("cost").getValue().toString();

                        data = dataSnapshot.child("data").getValue().toString();
                        AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);
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
    void paybutton()
    {
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("plans").child(plan.getText().toString());
                mDatabase.addChildEventListener(new ChildEventListener() {


                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if(i==0) {

                            cost = dataSnapshot.getValue().toString();
                            i++;

                            Intent i2 = new Intent(Main2Activity.this,Main3Activity.class);
                            i2.putExtra("cost",cost);
                            startActivity(i2);
                        }




                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


            }
        });

    }
    void changebutton()
    {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i2 = new Intent(Main2Activity.this,Main4Activity.class);
                 i2.putExtra("accno",accno);
                 startActivity(i2);
            }
        });
    }
}
