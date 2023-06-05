package com.app.wallit;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Todays_spending extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn;
    Spinner spinner;
    String text;
    int amount;
    Button button;
    EditText atm;
    FirebaseFirestore db;
    String cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_spending);
        btn = findViewById(R.id.back3);
        spinner = findViewById(R.id.actv);
        button = findViewById(R.id.add_btn);
        atm = findViewById(R.id.spendAmount);
        db = FirebaseFirestore.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // startActivity(new Intent(Todays_spending.this, home.class));
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(atm.getText().toString().length()==0){
                    Toast.makeText(Todays_spending.this, "Please enter some valid amount", Toast.LENGTH_SHORT).show();}
                else{
                amount = Integer.parseInt(atm.getText().toString());
                cat = spinner.getSelectedItem().toString();
                getHistory();
                //list obj = new list(amount,cat);
                //account.add_into_list(obj);
                String x = "amount "+ amount+ " is deducted";
                account.UpdateTotalBalance(amount);
                String b = ""+account.getTotalBalance();
                Date date = new Date();
                String c = amount+"#"+cat+"#"+date;
                String NewH = account.getHistory() +"_"+ c;
                //insertHistory("1", obj);
                insertdata(b,NewH);
                account.setUpdated(true);
               // account.setFirst(false);
                Toast.makeText(Todays_spending.this, x, Toast.LENGTH_SHORT).show();}

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void insertdata(String data, String l) {
        Map<String, Object> user = new HashMap<>();
        user.put("balace",data);
        user.put("list", l);
        db.collection("Wallit").document(account.getEmail()).set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       // makeText(Todays_spending.this, "Amount added", LENGTH_SHORT).show();
                        gettingdata_();
                        account.setDailyBudget();
                        finish();
                        //startActivity(new Intent(Todays_spending.this, home.class));
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
    public void getHistory() {
        DocumentReference df = db.collection("Wallit").document(account.getEmail());
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                account.setHistory(documentSnapshot.getString("list"));
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        account.setHistory("");
                    }
                });
    }

}