package com.example.harsh.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main7Activity extends AppCompatActivity {
EditText uname , pass ;
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        uname = findViewById(R.id.editText_email);
        pass = findViewById(R.id.editText_password);
        login = findViewById(R.id.button_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uname.getText().toString().equals("admin30199")) {
                      if(pass.getText().toString().equals("12345678"))
                      {
                          startActivity(new Intent(Main7Activity.this,Main9Activity.class));
                      }
                      else
                      {
                          Toast.makeText(Main7Activity.this, "INVALID PASSWORD", Toast.LENGTH_SHORT).show();
                      }

                }
                else
                    Toast.makeText(Main7Activity.this, "INVALID USERNAME", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
