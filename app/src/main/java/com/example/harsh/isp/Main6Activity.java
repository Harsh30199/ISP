package com.example.harsh.isp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main6Activity extends AppCompatActivity {
Button cust , admin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        cust = findViewById(R.id.button_cust);
        admin = findViewById(R.id.button_admin);
        cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main6Activity.this ,Main5Activity.class);
                startActivity(in);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main6Activity.this ,Main7Activity.class);
                startActivity(in);
            }
        });
    }
}
