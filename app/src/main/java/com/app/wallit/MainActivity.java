package com.app.wallit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.wallit.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    //Button login;
    TextView signuptxt;
    FirebaseFirestore fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fb = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //login = findViewById(R.id.login_btn);
        //signuptxt = findViewById(R.id.Textsign);
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            // startActivity(new Intent(MainActivity.this,home.class));
              String email = binding.Email.getText().toString().trim();
              account.setEmail(email);
              String password = binding.Password.getText().toString();
              progressDialog.show();
              firebaseAuth.signInWithEmailAndPassword(email,password)
                              .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                  @Override
                                  public void onSuccess(AuthResult authResult) {
                                      progressDialog.cancel();
                                      gettingdata();
                                      account.setDailyBudget();
                                      Toast.makeText(MainActivity.this, "login succesful", Toast.LENGTH_SHORT).show();
                                      startActivity(new Intent(MainActivity.this,home.class));

                                  }
                              })
                              .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.cancel();
                                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                              });

            }
        });
        binding.Textsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });
        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.Email.getText().toString().trim();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(MainActivity.this, "Password Reset Email send", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
    public void gettingdata(){
        DocumentReference df = fb.collection("Wallit").document(account.getEmail());
        df.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    account.setTotalBalance(document.getString("balace"));
                    account.setDailyBudget();
                } else {
                   return;
                }
            } else {
                Log.d( "Failed with: ", String.valueOf(task.getException()));
            }
        });
    }

}