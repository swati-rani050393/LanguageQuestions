package com.example.languageinterviewq.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.languageinterviewq.MainActivity;
import com.example.languageinterviewq.R;

public class FlutterQuestionActivity extends AppCompatActivity {
Button flutterPre,flutterNext,flutterUpNext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter_question);
        flutterPre=findViewById(R.id.flutterPre);
        flutterNext=findViewById(R.id.flutterNext);
        flutterUpNext=findViewById(R.id.flutterUpNext);

        flutterPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FlutterQuestionActivity.this, MainActivity.class));
            }
        });
        flutterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FlutterQuestionActivity.this, KotlinQuestionActivity.class));
            }
        });
        flutterUpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FlutterQuestionActivity.this, KotlinQuestionActivity.class));
            }
        });
    }
}