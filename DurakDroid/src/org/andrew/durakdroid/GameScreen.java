package org.andrew.durakdroid;

import android.graphics.Color;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameScreen extends Screen {

	public GameScreen(WebView view, WebViewClient client) {
		super(view, null);
		view.setBackgroundColor(Color.argb(1, 0, 0, 0));
		view.setWebViewClient(client);
        view.loadUrl("http://durak.time2play.mobi/landing");
	}

	public void reload() {
		((WebView) mView).reload();
	}

}
