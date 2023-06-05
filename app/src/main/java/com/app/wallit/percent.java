package com.app.wallit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.wallit.databinding.ActivityHistoryBinding;
import com.app.wallit.databinding.ActivityPercentBinding;

public class percent extends AppCompatActivity {
    ActivityPercentBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        Button btn;
        btn = findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(percent.this,before_history.class));
            }
        });

        account.List = account.populateList();
        account.Percent = account.getPercent();
        TextView food = findViewById(R.id.food_per);
        food.setText("0.0%");
        food.setText(account.Percent[1]+"%");
        TextView shop = findViewById(R.id.shopping_per);
        shop.setText("0.0%");
        shop.setText(account.Percent[2]+"%");
        TextView study = findViewById(R.id.studies_per);
        study.setText("0.0%");
        study.setText(account.Percent[3]+"%");
        TextView self= findViewById(R.id.self_per);
        self.setText("0.0%");
        self.setText(account.Percent[4]+"%");
        TextView out = findViewById(R.id.outing_per);
        out.setText("0.0%");
        out.setText(account.Percent[5]+"%");
        TextView other = findViewById(R.id.others_per);
        other.setText("0.0%");
        other.setText(account.Percent[6]+"%");
    }
}