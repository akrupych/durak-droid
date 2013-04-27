package org.andrew.durakdroid;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public abstract class Screen {

	protected View mView;
	protected View mProgressBar;
	
	public Screen(View view, View progressBar) {
		mView = view;
		mProgressBar = progressBar;
	}

	public void show() {
		mView.setVisibility(View.VISIBLE);
	}

	public boolean isVisible() {
		return mView.getVisibility() == View.VISIBLE;
	}

	public void hide() {
		mView.postDelayed(new Runnable() {
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
						mView.setVisibility(View.GONE);
					}
				});
				mView.startAnimation(fadeout);
			}
		}, 500);
	}

	public void setLoading(boolean state) {
		if (mProgressBar != null) {
			mProgressBar.setVisibility(state ? View.VISIBLE : View.GONE);
		}
	}

}
