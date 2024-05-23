package com.example.languageinterviewq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.languageinterviewq.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUser,edtConPass,edtEmail,edtPass;
    Button signUp;
    TextView havingAcc;
    FirebaseAuth auth;
    ProgressDialog dialog;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtUser=findViewById(R.id.signupUser);
        edtConPass=findViewById(R.id.signupCon);
        edtEmail=findViewById(R.id.signupEmail);
        edtPass=findViewById(R.id.signupPass);
        signUp=findViewById(R.id.signButton);
        havingAcc=findViewById(R.id.accountSignup);

        auth=FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();

        dialog=new ProgressDialog(this);
        dialog.setMessage("we are creating a new account");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,email,password,conpass;
                name=edtUser.getText().toString();
                email=edtEmail.getText().toString();
                password=edtPass.getText().toString();
                conpass=edtConPass.getText().toString();

                Users user=new Users(name,email,password,conpass);
                dialog.show();

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            String uid= Objects.requireNonNull(task.getResult().getUser()).getUid();
                            database.collection("Users")
                                    .document(uid)
                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                dialog.dismiss();
                                                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                                                finish();

                                            }
                                            else{
                                                Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                            Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        havingAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finishAffinity();
    }

}