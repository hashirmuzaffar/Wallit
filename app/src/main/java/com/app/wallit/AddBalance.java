package com.app.wallit;

import static android.widget.Toast.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.util.HashMap;
import java.util.Map;

public class AddBalance extends AppCompatActivity {
    Button add_amount;
    EditText get_amount;
    String value;
    TextView text1;
    Button back;
    FirebaseFirestore db;
    EditText text;
    String str;
    int atm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);
        back = findViewById(R.id.back);
        db = FirebaseFirestore.getInstance();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                account.setDailyBudget();
                //startActivity(new Intent(AddBalance.this, home.class));
            }
        });
        add_amount = findViewById(R.id.add_amount);
        text = (EditText) findViewById(R.id.get_amount);
        //text1 = findViewById(R.id.get_amount);
        str = text.getText().toString();
        //
        //    get_amount = findViewById(R.id.get_amount);
        //Editable atm = get_amount.getText();
        add_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // atm = Integer.parseInt(value);
                //account.setTotalBalance(text.getText().toString());
                //System.out.println(""+account.getTotalBalance());
                //account.setDailyBudget();
                if(text.getText().toString().length() != 0){
                account.addTotalBalance(text.getText().toString());
                String b = ""+account.getTotalBalance();
                insertdata(b);
                account.setDailyBudget();}
                else{
                    account.addTotalBalance("0");
                    makeText(AddBalance.this, "Please enter a valid amount", LENGTH_SHORT).show();
                }
                //text1.setText(text.getText().toString());
                //finish();
            }
        });

    }

    public void insertdata(String data) {
        Map<String, String> user = new HashMap<>();
        user.put("balace",data);
        db.collection("Wallit").document(account.getEmail()).set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        makeText(AddBalance.this, "Amount added", LENGTH_SHORT).show();
                        gettingdata_();
                        account.setDailyBudget();
                        finish();
                       // startActivity(new Intent(AddBalance.this, home.class));
                    }
                });
    }

    public void gettingdata_() {
        DocumentReference df = db.collection("Wallit").document(account.getEmail());
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                account.setTotalBalance(documentSnapshot.getString("balace"));
            }
        });
    }
}