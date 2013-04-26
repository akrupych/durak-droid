package org.andrew.durakdroid;

import android.view.View;
import android.view.View.OnClickListener;

public class ConnectionErrorScreen {

	private View mView;

	public ConnectionErrorScreen(View view, final Runnable onCheckConnection) {
		mView = view;
		view.findViewById(R.id.button_check_connection).setOnClickListener(
				new OnClickListener() {
			@Override
			public void onClick(View v) {
				onCheckConnection.run();
			}
		});
	}

	public void show() {
		mView.setVisibility(View.VISIBLE);
	}

	public boolean isVisible() {
		return mView.getVisibility() == View.VISIBLE;
	}

	public void hide() {
		FadeoutAnimation.startOn(mView);
	}

}
