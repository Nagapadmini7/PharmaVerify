package com.example.pharmaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.pharmaapp.Models.User;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText userName;
    EditText password;
    EditText email;
    Button signUp;
    TextView login_in;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        signUp = findViewById(R.id.sign_up_btn);
        login_in = findViewById(R.id.sign_in);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We are creating your account");
//        getSupportActionBar().hide();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getname = userName.getText().toString().trim();
                String getmail = email.getText().toString().trim();
                String getPass = password.getText().toString().trim();

                sharedPreferences = getSharedPreferences("OnBoardActivity", MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean("FirstTime", true);

//                if(isFirstTime) {
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("FirstTime", false);
//                    editor.commit();
//
//                    Intent intent = new Intent(SignUp.this, HomePage.class);
//                    startActivity(intent);
//                    finish();
//                }

                // Check if user is signed in (non-null) and update UI accordingly.


                if (TextUtils.isEmpty(getmail) || TextUtils.isEmpty(getPass) || TextUtils.isEmpty(getname)) {
                    Toast.makeText(SignUp.this, "Enter your details", Toast.LENGTH_SHORT).show();
                }
                try {
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                User user = new User(userName.getText().toString(), email.getText().toString(), password.getText().toString());
                                String id = task.getResult().getUser().getUid();
                                firebaseDatabase.getReference().child("Users").child(id).setValue(user);
                                Intent intent2 = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent2);
                                Toast.makeText(SignUp.this, "User created successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                catch (Exception e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        });



        login_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}