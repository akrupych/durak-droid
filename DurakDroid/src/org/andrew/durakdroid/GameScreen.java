package org.andrew.durakdroid;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameScreen extends Screen {
	
	private WebView mWebView;

	@SuppressLint("SetJavaScriptEnabled")
	public GameScreen(WebView view, WebViewClient client) {
		super(view, null);
		mWebView = (WebView) mView;
		mWebView.setBackgroundColor(Color.argb(1, 0, 0, 0));
		mWebView.setWebViewClient(client);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://durak.time2play.mobi");
	}

	public void reload() {
		mWebView.reload();
	}

	public void removeBanner() {
		mWebView.loadUrl("javascript:(function() { document" +
				".getElementsByTagName('a')[0].parentNode.style.display =" +
				" 'none'; })()");
	}

	public boolean goBack() {
		if (mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return false;
	}

}
