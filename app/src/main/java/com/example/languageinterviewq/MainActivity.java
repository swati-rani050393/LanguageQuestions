package com.example.languageinterviewq;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.languageinterviewq.Adapters.CategoryAdapter;
import com.example.languageinterviewq.Models.Category;
import com.example.languageinterviewq.Screens.JavaQuestionActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore database;
    RecyclerView catRecycler;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "MainActivity";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView=findViewById(R.id.adView);
        catRecycler=findViewById(R.id.item_recycler);


        if (!NetworkUtils.isInternetAvailable(this)) {
            showNoInternetDialog();
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
               Toast.makeText(MainActivity.this, " successful ", Toast.LENGTH_LONG).show();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        Log.e("----A-----",adRequest.toString());
        Toast.makeText(this, ""+adRequest, Toast.LENGTH_SHORT).show();
        mAdView.loadAd(adRequest);


    scheduleInterstitialAds();




        database=FirebaseFirestore.getInstance();
        final ArrayList<Category> categories = new ArrayList<>();


        final CategoryAdapter adapter = new CategoryAdapter(this, categories);
        database.collection("Categories").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                categories.clear();
                assert value != null;
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    Category modal = snapshot.toObject(Category.class);
                    assert modal != null;
                    modal.setCategoryId(snapshot.getId());
                    categories.add(modal);
                }
                adapter.notifyDataSetChanged();
            }
        });
        catRecycler.setLayoutManager(new GridLayoutManager(this,2));
        catRecycler.setAdapter(adapter);

    }
    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-3844199791512298/9252527658", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }

    private void showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.");
        }
    }

    private void scheduleInterstitialAds() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Load the next interstitial ad
                loadInterstitialAd();
                // Show the loaded interstitial ad
                showInterstitialAd();
                // Schedule the next ad to be shown after 2 minutes. 1 min = 60000 milliseconds
                handler.postDelayed(this, 1200000); // 2 minutes in milliseconds
            }
        }, 60000); // Delay the first ad display for 30 seconds
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Internet Error")
        .setMessage("Please turn on your Internet connection.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (NetworkUtils.isInternetAvailable(MainActivity.this)) {
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Internet is still not available.Please turn on your Internet Connection", Toast.LENGTH_SHORT).show();
                            showNoInternetDialog(); // Show the dialog again
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Close the app
                        finishAffinity();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



}