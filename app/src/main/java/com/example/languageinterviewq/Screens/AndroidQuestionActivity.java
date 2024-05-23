package com.example.languageinterviewq.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.languageinterviewq.MainActivity;
import com.example.languageinterviewq.R;

public class AndroidQuestionActivity extends AppCompatActivity {
 Button andPre,andNext,andUpNext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_question);

        andPre=findViewById(R.id.androidPre);
        andNext=findViewById(R.id.androidNext);
        andUpNext=findViewById(R.id.andUpNext);

        andPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AndroidQuestionActivity.this, MainActivity.class));
            }
        });
        andNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AndroidQuestionActivity.this,DartQuestionActivity.class));
            }
        });
        andUpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AndroidQuestionActivity.this,DartQuestionActivity.class));
            }
        });
    }
}