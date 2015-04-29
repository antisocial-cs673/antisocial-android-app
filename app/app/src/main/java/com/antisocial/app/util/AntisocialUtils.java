package com.antisocial.app.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class AntisocialUtils {

	public static boolean isInternetConnected(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        return conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected();            
	}
	
	public static boolean isInternetConnected(Fragment context) {
		return isInternetConnected(context.getActivity());
	}
	
	/**
	 * Hides the soft keyboard
	 */
	public static void hideSoftKeyboard(Activity activity) {
	    if(activity.getCurrentFocus()!=null) {
	        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	    }
	}
	
	/**
	 * Shows the soft keyboard
	 */
	public static void showSoftKeyboard(View view) {
	    InputMethodManager inputMethodManager = (InputMethodManager) ((Activity) view.getContext()).getSystemService(Activity.INPUT_METHOD_SERVICE);
	    view.requestFocus();
	    inputMethodManager.showSoftInput(view, 0);
	    
	}	
}
