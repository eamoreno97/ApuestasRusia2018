package com.example.andresacosta.apuestasrusia2018;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SigIn extends AppCompatActivity {
    EditText Email;
    EditText Name;
    EditText Password;
    EditText confirmPassword;
    Button Sign_In;

    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_sig_in);

        Name = findViewById(R.id.editName);
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editConfirm);

        Sign_In = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
            }
        });
    }

    private void writeNewUser(String name, String email){
        Element user = new Element(name, email, 100);
        mDatabase.child("users").push().setValue(user);
    }

    private void validar(){
        mAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            writeNewUser(Name.getText().toString(),Email.getText().toString());
                        }else{
                            Log.d(TAG,"createUserWithEmail:failure",task.getException());
                            Toast.makeText(SigIn.this,"Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user){
        if(user!=null){
            Toast.makeText(SigIn.this,"Successful user registration.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
