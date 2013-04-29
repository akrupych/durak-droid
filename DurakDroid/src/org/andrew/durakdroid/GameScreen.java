package org.andrew.durakdroid;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameScreen extends Screen {

	@SuppressLint("SetJavaScriptEnabled")
	public GameScreen(WebView view, WebViewClient client) {
		super(view, null);
		view.setBackgroundColor(Color.argb(1, 0, 0, 0));
		view.setWebViewClient(client);
		view.getSettings().setJavaScriptEnabled(true);
		view.loadUrl("http://durak.time2play.mobi");
	}

	public void reload() {
		((WebView) mView).reload();
	}

	public void removeBanner() {
		((WebView) mView).loadUrl("javascript:(function() { " +
				"document.getElementsByTagName('a')[0].parentNode.style.display =" +
				" 'none'; })()");
	}

}
