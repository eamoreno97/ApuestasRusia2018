package com.example.andresacosta.apuestasrusia2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {
    Button Sign_In;
    Button Expect_user;
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Sign_In = findViewById(R.id.btnSignIn2);
        Expect_user = findViewById(R.id.btnGo);
        mAuth = FirebaseAuth.getInstance();

        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
                String message = "Hello";
                intent.putExtra("Well",message);
                startActivity(intent);
            }
        });
        Expect_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, TabMain_User.class);
                String message = "Hello";
                intent.putExtra("Well",message);
                startActivity(intent);
            }
        });
    }
}
