package com.example.languageinterviewq.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.languageinterviewq.MainActivity;
import com.example.languageinterviewq.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class JavaQuestionActivity extends AppCompatActivity {
    private Button javaPre, javaNext, javaUpNext;
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "JavaQuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_question);

        javaPre = findViewById(R.id.javaPre);
        javaNext = findViewById(R.id.javaNext);
        javaUpNext = findViewById(R.id.javaUpNext);
      //showInterstitialAd();
        // Load the initial interstitial ad
//        loadInterstitialAd();
//         showInterstitialAd();
        javaPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JavaQuestionActivity.this, MainActivity.class));
            }
        });

        javaNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JavaQuestionActivity.this, AndroidQuestionActivity.class));
            }
        });

        javaUpNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JavaQuestionActivity.this, AndroidQuestionActivity.class));
            }
        });

    }

}

