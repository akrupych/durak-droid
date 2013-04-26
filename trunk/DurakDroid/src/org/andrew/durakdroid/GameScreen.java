package org.andrew.durakdroid;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameScreen extends Screen {

	public GameScreen(WebView view, WebViewClient client) {
		super(view);
		view.setWebViewClient(client);
        view.loadUrl("http://durak.time2play.mobi/landing");
	}

	public void reload() {
		((WebView) mView).reload();
	}

}
