package com.example.andresacosta.apuestasrusia2018;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button Sign_In;
    Button Log_In;
    EditText Email;
    EditText Password;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "ElTagaso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sign_In = findViewById(R.id.btnLogin);
        Log_In = findViewById(R.id.btnRegister);
        Email = findViewById(R.id.txtEmail);
        Password = findViewById(R.id.txtPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SigIn.class);
                String message = "Hello";
                intent.putExtra("Well",message);
                startActivity(intent);
            }
        });
        Log_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguearse();
            }
        });
    }

    private void loguearse(){
        firebaseAuth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"signInWithEmail:succes");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        }else{
                            Log.d(TAG,"signInWithEmail:failure",task.getException());
                            Toast.makeText(MainActivity.this, "Failed to LogIn.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user){
        if(user!=null){
            Toast.makeText(MainActivity.this, "Successful LogIn",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainTab.class);
            String message = Email.getText().toString();
            intent.putExtra("Well", message);
            startActivity(intent);
        }
    }
}
