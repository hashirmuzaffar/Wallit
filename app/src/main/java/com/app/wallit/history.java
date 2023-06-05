package com.app.wallit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.app.wallit.databinding.ActivityHistoryBinding;
import com.app.wallit.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    static ActivityHistoryBinding bind;
    FirebaseFirestore db;
    //ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        db = FirebaseFirestore.getInstance();
        bind.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(history.this,before_history.class));
            }
        });
        getHistory();
        if(account.getHistory()!=null){
        account.List = account.populateList();
        if (account.List.size() != 0) {
            ListViewAdapter LVA = new ListViewAdapter(getApplicationContext(), account.List);
            bind.listview.setAdapter(LVA);
            account.setFirst(false);//}
        }
    }

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