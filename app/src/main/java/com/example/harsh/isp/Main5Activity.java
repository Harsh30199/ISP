package com.example.harsh.isp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main5Activity extends AppCompatActivity {
EditText user , pass, accno;
Button login , register ;
String email , password , plan ;
    private DatabaseReference mDatabase , mpassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mAuth = FirebaseAuth.getInstance();
        pass = findViewById(R.id.editText_password);
        login = findViewById(R.id.button_login);
        accno = findViewById(R.id.editText_accno);
        register = findViewById(R.id.button_register);
        final int[] i = {0};
        i[0] = 0 ;
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {

                                         mpassword = FirebaseDatabase.getInstance().getReference().child("users").child(accno.getText().toString());
                                         mpassword.addChildEventListener(new ChildEventListener() {


                                             @Override
                                             public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                                    if(i[0]==0) {
                                                        email = dataSnapshot.getValue().toString();

                                                        i[0]++;
                                                    }
                                                    else if(i[0]== 1)
                                                    {
                                                        password =dataSnapshot.getValue().toString();

                                                        i[0]++;
                                                    } else if (i[0] == 2
                                                            ) {
                                                        plan = dataSnapshot.getValue().toString();

                                                    }
                                                 final Handler handler = new Handler();
                                                 handler.postDelayed(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                     check();
                                                     }
                                                 }, 100);


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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main5Activity.this,Main11Activity.class));
            }
        });
    }
    void check()
    {
        if (pass.getText().toString().equals(password)) {
            Intent i = new Intent(Main5Activity.this, Main2Activity.class);
            i.putExtra("plan", plan);
            i.putExtra("accno",accno.getText().toString());
            startActivity(i);
        }
        else
        {
            Toast.makeText(Main5Activity.this, "INVALID PASSWORD", Toast.LENGTH_SHORT).show();
        }
    }
    }
