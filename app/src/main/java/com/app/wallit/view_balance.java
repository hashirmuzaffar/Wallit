package com.app.wallit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class view_balance extends AppCompatActivity {
    Button back;
    Button close;
    EditText balacne;
    FirebaseFirestore fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_balance);
        back = findViewById(R.id.back);
        close = findViewById(R.id.close_btn);
        balacne = findViewById(R.id.set_balance);
        fb = FirebaseFirestore.getInstance();
        balacne.setText(String.valueOf(account.getTotalBalance()));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(view_balance.this, home.class));
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // startActivity(new Intent(view_balance.this, home.class));
            }
        });

    }

}