package org.andrew.durakdroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected static final String TAG = MainActivity.class.getName();
    
    private boolean mIsLoaded = false;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View splashScreen = findViewById(R.id.splash_screen);
        final View errorScreen = findViewById(R.id.error_screen);
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
            		splashScreen.postDelayed(new Runnable() {
						@Override
						public void run() {
		        			Animation fadeout = new AlphaAnimation(1, 0);
		        			fadeout.setDuration(1000);
		        			fadeout.setAnimationListener(new AnimationListener() {
								@Override
								public void onAnimationStart(Animation animation) { }
								@Override
								public void onAnimationRepeat(Animation animation) { }
								@Override
								public void onAnimationEnd(Animation animation) {
									splashScreen.setVisibility(View.GONE);
								}
							});
							splashScreen.startAnimation(fadeout);
						}
					}, 1000);
        			mIsLoaded = true;
        		}
        	}
        	@Override
        	public void onReceivedError(WebView view, int errorCode,
        			String description, String failingUrl) {
        		Log.e(TAG, "onReceivedError: " + description);
        		errorScreen.setVisibility(View.VISIBLE);
        		errorScreen.setTag(failingUrl);
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
        Button checkConnection = (Button) findViewById(R.id.button_check_connection);
        checkConnection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				errorScreen.setVisibility(View.GONE);
				webView.loadUrl(errorScreen.getTag().toString());
			}
		});
    }
    
}
