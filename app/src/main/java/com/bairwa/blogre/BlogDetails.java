package com.bairwa.blogre;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BlogDetails extends AppCompatActivity {
WebView webView;
ProgressBar progressBar;

public static final String TAG=".BlogDetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);
        String urlw = "";
getSupportActionBar().hide();
        if (urlw !=null){
      urlw =getIntent().getStringExtra("blogurl");}
        Log.d(TAG, "onCreate: "+urlw);
      //  Toast.makeText(this, getIntent().getStringExtra("blogurl"), Toast.LENGTH_SHORT).show();
    webView=findViewById(R.id.webview);
    progressBar=findViewById(R.id.prog);

    webView.setVisibility(View.INVISIBLE);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebChromeClient(new WebChromeClient());
    webView.setWebViewClient(new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Toast.makeText(BlogDetails.this, "loading", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Toast.makeText(BlogDetails.this, "finished", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    });
webView.loadUrl(urlw);
    }
}
