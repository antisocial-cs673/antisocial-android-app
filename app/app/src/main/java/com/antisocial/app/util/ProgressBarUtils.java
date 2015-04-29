package com.antisocial.app.util;

import com.antisocial.app.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

public class ProgressBarUtils {
	
	public static ProgressDialog showProgressDialog(Activity activity, int messageId) {
		return showProgressDialog(activity, messageId, R.string.title_loading);
	}
	
	public static ProgressDialog showProgressDialog(Activity activity,  int messageId, int titleId) {

		final ViewGroup rootView = (ViewGroup) ((ViewGroup)activity.getWindow().getDecorView().getRootView()).getChildAt(0);
		for(int i=0; i<((ViewGroup)rootView).getChildCount(); ++i) {
		    View nextChild = ((ViewGroup)rootView).getChildAt(i);
		    nextChild.setVisibility(View.GONE);
		}
		
		ProgressDialog dialog = ProgressDialog.show(activity, activity.getResources().getString(titleId) , activity.getResources().getString(messageId), true);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				for(int i=0; i<((ViewGroup)rootView).getChildCount(); ++i) {
				    View nextChild = ((ViewGroup)rootView).getChildAt(i);
				    nextChild.setVisibility(View.VISIBLE);
				}
			}
		});
		dialog.setCancelable(true);
		
		return dialog;
	}
	
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static void showProgressBar(final View rootView, final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		
		Activity activity = (Activity)rootView.getContext();
		ProgressBar existingProgress = null;//(ProgressBar)activity.findViewById(R.id.);
		final ProgressBar progress;
		
		if(existingProgress == null) {
			progress = new ProgressBar(activity);
					
			//progress.setId(R.id.progress_circular);
			activity.addContentView(progress, new LayoutParams(rootView.getWidth(), rootView.getHeight()));
		} else {
			progress = existingProgress;
		}
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = activity.getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			if(rootView instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup)rootView;
				for (int i = 0; i < vg.getChildCount(); i++) {
					final View hideView = vg.getChildAt(i);

					hideView.setVisibility(show ? View.GONE : View.VISIBLE);
					hideView.animate().setDuration(shortAnimTime)
							.alpha(show ? 0 : 1)
							.setListener(new AnimatorListenerAdapter() {
								@Override
								public void onAnimationEnd(Animator animation) {
									hideView.setVisibility(show ? View.GONE
											: View.VISIBLE);
								}
							});

				}
			}
			progress.setVisibility(show ? View.VISIBLE : View.GONE);
			progress.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							progress.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			progress.setVisibility(show ? View.VISIBLE : View.GONE);
			
			if(rootView instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup)rootView;
				for (int i = 0; i < vg.getChildCount(); i++) {
					final View hideView = vg.getChildAt(i);
	
					hideView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			}
		}
	}
}
