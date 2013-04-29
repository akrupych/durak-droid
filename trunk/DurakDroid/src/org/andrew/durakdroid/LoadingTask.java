package org.andrew.durakdroid;

import java.net.URL;

import org.apache.commons.io.IOUtils;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

public class LoadingTask extends AsyncTask<String, Void, String> {

	private static final String TAG = LoadingTask.class.getName();
	
	private String mUrl;
	private WebView mWebView;

	public LoadingTask(WebView view) {
		mWebView = view;
	}

	@Override
	protected String doInBackground(String... params) {
		Log.d(TAG, "loading started");
		mUrl = params[0];
		try {
			StringBuilder html = new StringBuilder(
					IOUtils.toString(new URL(mUrl).openStream()));
			int bannerStart = html.indexOf(
					"<a href=\"http://time2play.mobi\">");
			int bannerEnd = html.indexOf("</a>", bannerStart) + 4;
			if (bannerStart >= 0 && bannerEnd >= 0) {
				html.delete(bannerStart, bannerEnd);
			}
			return html.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(String result) {
		Log.d(TAG, "loading finished");
		mWebView.loadDataWithBaseURL("http://durak.time2play.mobi",
				result, "text/html", "utf-8", null);
	}
	
}
