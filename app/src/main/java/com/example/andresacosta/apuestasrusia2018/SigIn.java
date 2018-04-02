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

    private FirebaseAuth firebaseAuth;
    private static final String TAG = "ElTagaso";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editConfirm);
        Name = findViewById(R.id.editName);
        Sign_In = findViewById(R.id.btnRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }

    private void writeNewUser(String name, String email){
        Element user = new Element(name, email, 100);
        databaseReference.child("users").push().setValue(user);
    }

    private void validar(){
        firebaseAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
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
