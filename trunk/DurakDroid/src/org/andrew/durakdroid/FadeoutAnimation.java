package org.andrew.durakdroid;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class FadeoutAnimation {

	public static void startOn(final View view) {
		view.postDelayed(new Runnable() {
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
						view.setVisibility(View.GONE);
					}
				});
				view.startAnimation(fadeout);
			}
		}, 500);
	}

}
