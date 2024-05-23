package com.example.languageinterviewq;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {
    EditText emailLog,password;
    TextView forgetEdt,accountLog;
    Button logBtn;

    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;
    OnBackPressedDispatcher onBackPressedDispatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        emailLog=findViewById(R.id.edtEmail);
        password=findViewById(R.id.edtPass);
        forgetEdt=findViewById(R.id.forgetEdt);
        accountLog=findViewById(R.id.accountlog);
        logBtn=findViewById(R.id.logInButton);

        if (!NetworkUtils.isInternetAvailable(this)) {
            showNoInternetDialog();
        }
        SharedPreferences sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        boolean islogged = sharedPreferences.getBoolean("isLogin",false);
        if (islogged)
        {

            startActivity(new Intent(LogInActivity.this,MainActivity.class));

        }
        auth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Logging in...");

        forgetEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  emailL=emailLog.getText().toString();
                if(emailL.isEmpty())
                {
                    emailLog.setError("Please enter your Email");
                    emailLog.requestFocus();
                }
                else{
                    auth.sendPasswordResetEmail(emailL)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(
                                                LogInActivity.this,
                                                "Password reset email sent. Check your inbox.",
                                                Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(LogInActivity.this,
                                                "Failed to send password reset email."+task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             if(ActivityCompat.checkSelfPermission(LogInActivity.this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
//               ActivityCompat.requestPermissions(LogInActivity.this,new String[]{Manifest.permission.INTERNET},0);
//             }


                String email, pass;
                email= emailLog.getText().toString();
                pass = password.getText().toString();
                dialog.show();
                SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("email",email);
                editor.putString("pass",pass);
                editor.putBoolean("isLogin",true);
                editor.apply();


                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            // Successfully logged in

                            // Add the Firestore document retrieval here
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            DocumentReference userDocRef = db.collection("Users").document(auth.getCurrentUser().getUid());

                            userDocRef.get().addOnCompleteListener(documentTask -> {
                                if (documentTask.isSuccessful()) {
                                    DocumentSnapshot document = documentTask.getResult();
                                    if (document.exists()) {
                                        String storedEmail = document.getString("email");

                                        // Compare the entered email with the stored email
                                        if (storedEmail != null && storedEmail.equals(email)) {
                                            // Emails match, proceed to the MainActivity
                                            startActivity(new Intent(LogInActivity.this, MainActivity.class));
                                            finish();
                                        } else {
                                            // Emails do not match, show an error message
                                            Toast.makeText(LogInActivity.this, "Email mismatch", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    // Task failed with an exception
                                    Exception exception = documentTask.getException();
                                    // Handle the exception
                                }
                            });
                        } else {
                            // Login failed
                            Toast.makeText(LogInActivity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
        accountLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
            }
        });


    }

    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("InternetError")
        .setMessage("Please turn on your Internet connection.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (NetworkUtils.isInternetAvailable(LogInActivity.this)) {
                            dialog.dismiss();
                        } else {
                            Toast.makeText(LogInActivity.this, "Internet is still not available.", Toast.LENGTH_SHORT).show();
                            showNoInternetDialog(); // Show the dialog again
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
       finishAffinity();
    }

    }
