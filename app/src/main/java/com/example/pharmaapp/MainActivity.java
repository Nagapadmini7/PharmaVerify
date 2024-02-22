package com.example.pharmaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button signIn;
    TextView signUp;
    TextView forgot_pass;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in_btn);
        signUp = findViewById(R.id.sign_up);
        forgot_pass = findViewById(R.id.forgot_password);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Logging in");
        progressDialog.setMessage("Logging in to your account");
        mAuth = FirebaseAuth.getInstance();

//        getSupportActionBar().hide();
        checkBox();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getmail = email.getText().toString().trim();
                String getPass = password.getText().toString().trim();
                if (TextUtils.isEmpty(getmail) && !TextUtils.isEmpty(getPass)) {
                    Toast.makeText(MainActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(getPass) && !TextUtils.isEmpty(getmail)) {
                    Toast.makeText(MainActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(getmail) && TextUtils.isEmpty(getPass)) {
                    Toast.makeText(MainActivity.this, "Enter your email and password", Toast.LENGTH_SHORT).show();
                }

                try {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name", "true");
                                editor.apply();
                                Intent intent = new Intent(MainActivity.this, Product.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchMethod();
            }
        });
    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name", "");
        if(check.equals("true")) {
            Intent intent = new Intent(MainActivity.this, Product.class);
            startActivity(intent);
        }
    }

    private void fetchMethod() {
        if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter your registered email", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.fetchSignInMethodsForEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()) {
                            SignInMethodQueryResult result = task.getResult();
                            if (result.getSignInMethods() != null) {
                                // User found, proceed with password reset
                                resetPassword(email.getText().toString());
                            }
                            else {
                                // No user found with this email
                                Toast.makeText(MainActivity.this, "No account with this email exists", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            // Failed to check for existing user
                            Toast.makeText(MainActivity.this, "Failed to check for existing user", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    private void resetPassword(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}