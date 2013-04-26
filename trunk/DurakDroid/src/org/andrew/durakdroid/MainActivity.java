package org.andrew.durakdroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    protected static final String TAG = MainActivity.class.getName();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View splashScreen = findViewById(R.id.splash_screen);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl("http://durak.time2play.mobi/landing");
        webView.setWebViewClient(new WebViewClient() {
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon) {
        		Log.d(TAG, "onPageStarted: " + url);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url) {
        		Log.d(TAG, "onPageFinished: " + url);
        		splashScreen.setVisibility(View.GONE);
        	}
        	@Override
        	public void onReceivedError(WebView view, int errorCode,
        			String description, String failingUrl) {
        		Log.e(TAG, "onReceivedError: " + description);
        	}
        });
    }
    
}
