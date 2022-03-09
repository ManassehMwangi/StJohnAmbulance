package com.stjohnambulance.m_aidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView app, login, here;
    EditText email2, pass2;
    Button login2;
    ProgressBar progress2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = findViewById(R.id.text4);
        login = findViewById(R.id.text5);
        here = findViewById(R.id.text6);
        email2 = findViewById(R.id.editE);
        pass2 = findViewById(R.id.editP);
        login2 = findViewById(R.id.btnLog);
        progress2 = findViewById(R.id.progress);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email2.getText().toString().trim();
                String mPass = pass2.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)) {
                    email2.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(mPass)) {
                    pass2.setError("Password is required");
                    return;
                }

                if (pass2.length() < 6){
                    pass2.setError("Password must be 6 ar more characters");
                }
                progress2.setVisibility(View.VISIBLE);
                // register user in firebase

                fAuth.signInWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(login.this, "Error occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progress2.setVisibility(View.GONE);
                        }

                    }
                });


            }

        });

        here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        });

    }
}