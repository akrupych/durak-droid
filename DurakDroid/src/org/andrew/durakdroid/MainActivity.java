package org.andrew.durakdroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final String TAG = MainActivity.class.getName();

	private SplashScreen mSplashScreen;
	private GameScreen mGameScreen;
	private ConnectionErrorScreen mErrorScreen;
	private Screen mCurrentScreen;

	private ConnectivityManager mConnManager;

	private WebViewClient mClient = new WebViewClient() {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			Log.d(TAG, "onPageStarted: " + url);
			mCurrentScreen.setLoading(true);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			Log.d(TAG, "onPageFinished: " + url);
			mGameScreen.removeBanner();
			mCurrentScreen.setLoading(false);
			if (!isNetworkAvailable()) {
				switchTo(mErrorScreen);
			} else {
				switchTo(mGameScreen);
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.d(TAG, "shouldOverrideUrlLoading: " + url);
			if (!Uri.parse(url).getHost().contains("time2play.mobi")) {
				Toast.makeText(MainActivity.this, "You can't go there",
						Toast.LENGTH_SHORT).show();
				return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSplashScreen = new SplashScreen(findViewById(R.id.splash_screen));
		switchTo(mSplashScreen);
		mGameScreen = new GameScreen((WebView)
				findViewById(R.id.web_view), mClient);
		mErrorScreen = new ConnectionErrorScreen(
				findViewById(R.id.error_screen), new Runnable() {
					@Override
					public void run() {
						mGameScreen.reload();
					}
				});
	}

	private void switchTo(Screen screen) {
		if (mCurrentScreen != screen) {
			if (mCurrentScreen != null) {
				mCurrentScreen.hide();
			}
			mCurrentScreen = screen;
			mCurrentScreen.show();
		}
	}

	private boolean isNetworkAvailable() {
		if (mConnManager == null) {
			mConnManager = (ConnectivityManager)
					getSystemService(Context.CONNECTIVITY_SERVICE);
		}
		return mConnManager.getActiveNetworkInfo() != null;
	}

}
