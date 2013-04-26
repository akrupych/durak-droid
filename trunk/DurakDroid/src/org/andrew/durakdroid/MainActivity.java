package org.andrew.durakdroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    protected static final String TAG = MainActivity.class.getName();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://durak.time2play.mobi/landing");
        webView.setWebViewClient(new WebViewClient() {
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon) {
        		Log.d(TAG, "onPageStarted: " + url);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url) {
        		Log.d(TAG, "onPageFinished: " + url);
        	}
        });
    }
    
}
