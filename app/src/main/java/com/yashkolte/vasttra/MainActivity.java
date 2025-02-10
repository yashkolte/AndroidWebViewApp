package com.yashkolte.vasttra;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    WebView myWeb;
    ImageView noInternetImage;
    Button refreshButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        myWeb = findViewById(R.id.myWeb);

        // Disable WebView overscroll effect
        myWeb.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        myWeb = findViewById(R.id.myWeb);
        noInternetImage = findViewById(R.id.noInternetImage);
        refreshButton = findViewById(R.id.refreshButton);
        progressBar = findViewById(R.id.progressBar);

        checkInternetAndLoad();

        // Refresh button click listener
        refreshButton.setOnClickListener(view -> checkInternetAndLoad());

        // Show progress bar while loading pages
        myWeb.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkInternetAndLoad();  // Auto-refresh when the app is reopened
    }

    private void checkInternetAndLoad() {
        if (isInternetAvailable()) {
            myWeb.getSettings().setJavaScriptEnabled(true);
            myWeb.setWebViewClient(new WebViewClient());
            myWeb.loadUrl("https://vasttram.com/");

            myWeb.setVisibility(View.VISIBLE);
            noInternetImage.setVisibility(View.GONE);
            refreshButton.setVisibility(View.GONE);
        } else {
            myWeb.setVisibility(View.GONE);
            noInternetImage.setVisibility(View.VISIBLE);
            refreshButton.setVisibility(View.VISIBLE);
        }
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            return capabilities != null &&
                    (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }
        return false;
    }

    // Handle back button navigation in WebView
    @Override
    public void onBackPressed() {
        if (myWeb.canGoBack()) {
            myWeb.goBack();
        } else {
            super.onBackPressed(); // Exit the app if no more pages to go back to
        }
    }
}
