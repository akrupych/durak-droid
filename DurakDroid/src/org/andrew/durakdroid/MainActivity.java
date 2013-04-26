package org.andrew.durakdroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected static final String TAG = MainActivity.class.getName();

	private ConnectivityManager mConnManager;
    private ConnectionErrorScreen mErrorScreen;
    
    private boolean mIsLoaded = false;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConnManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final View splashScreen = findViewById(R.id.splash_screen);
        final WebView webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl("http://durak.time2play.mobi/landing");
        webView.setWebViewClient(new WebViewClient() {
        	@Override
        	public void onPageStarted(WebView view, String url, Bitmap favicon) {
        		Log.d(TAG, "onPageStarted: " + url);
        	}
        	@Override
        	public void onPageFinished(WebView view, String url) {
        		Log.d(TAG, "onPageFinished: " + url);
        		if (!mIsLoaded) {
        			FadeoutAnimation.startOn(splashScreen);
        			mIsLoaded = true;
        		}
        		if (mConnManager.getActiveNetworkInfo() == null) {
        			mErrorScreen.show();
        		} else if (mErrorScreen.isVisible()) {
        			mErrorScreen.hide();
        		}
        	}
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		Log.e(TAG, "shouldOverrideUrlLoading: " + url);
        		if (!Uri.parse(url).getHost().equals("durak.time2play.mobi")) {
        			Toast.makeText(MainActivity.this, "You can't go there", Toast.LENGTH_SHORT).show();
                    return true;
                }
        		return false;
        	}
        });
        mErrorScreen = new ConnectionErrorScreen(
        		findViewById(R.id.error_screen),
        		new Runnable() {
					@Override
					public void run() {
						webView.reload();
					}
				});
    }
    
}
