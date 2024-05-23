package com.example.languageinterviewq.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.languageinterviewq.MainActivity;
import com.example.languageinterviewq.R;

public class DartQuestionActivity extends AppCompatActivity {
Button dartPre,dartNext,dartUPNext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dart_question);
        dartPre=findViewById(R.id.dartPre);
        dartNext=findViewById(R.id.dartNext);
        dartUPNext=findViewById(R.id.dartUpNext);

        dartPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DartQuestionActivity.this, MainActivity.class));
            }
        });
        dartNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DartQuestionActivity.this, FlutterQuestionActivity.class));
            }
        });
       dartUPNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DartQuestionActivity.this, FlutterQuestionActivity.class));
            }
        });
    }
}