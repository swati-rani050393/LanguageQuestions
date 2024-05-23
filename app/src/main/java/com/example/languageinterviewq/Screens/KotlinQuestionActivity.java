package com.example.languageinterviewq.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.languageinterviewq.MainActivity;
import com.example.languageinterviewq.R;

public class KotlinQuestionActivity extends AppCompatActivity {
 Button kotlinPre,kotlinUPPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin_question);
        kotlinPre=findViewById(R.id.kotlinPre);
        kotlinUPPre=findViewById(R.id.kotlinUpPre);

       kotlinPre.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(KotlinQuestionActivity.this, MainActivity.class));
           }
       });

        kotlinUPPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KotlinQuestionActivity.this, MainActivity.class));
            }
        });
    }
}