package org.andrew.durakdroid;

import android.view.View;
import android.view.View.OnClickListener;

public class ConnectionErrorScreen extends Screen {

	public ConnectionErrorScreen(View view, final Runnable onCheckConnection) {
		super(view);
		view.findViewById(R.id.button_check_connection).setOnClickListener(
				new OnClickListener() {
			@Override
			public void onClick(View v) {
				onCheckConnection.run();
			}
		});
	}

}
