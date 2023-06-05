package com.app.wallit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class home extends AppCompatActivity {
    EditText name;
    EditText Budget;
    CardView button1;
    CardView button2;
    CardView button3;
    CardView button4;
    CardView refresh;
    CardView button5;
    FirebaseFirestore fb;
    view_balance vb;
    Button help;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //name = findViewById(R.id.PersonName);
        Budget = findViewById(R.id.DailyBudget);
        fb = FirebaseFirestore.getInstance();
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.todo);
        refresh = findViewById(R.id.refresh);
        help = findViewById(R.id.help);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account.setDailyBudget();
                Budget.setText(""+ account.getDailyBudget() +"Rs");
            }
        });
        account.setDailyBudget();
        Budget.setText(""+ account.getDailyBudget() +"Rs");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //name.setText("Hashir's WALLIT");
                // Budget.setText(""+ account.getDailyBudget() +"Rs");
               // finish();
                startActivity(new Intent(home.this, AddBalance.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // gettingdata();
                //finish();
                startActivity(new Intent(home.this, view_balance.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, Todays_spending.class));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHistory();
                account.List = account.populateList();
                startActivity(new Intent(home.this, before_history.class));
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, distract.class));
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, manual.class));
            }
        });
    }

    public void getHistory() {
        DocumentReference df = fb.collection("Wallit").document(account.getEmail());
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