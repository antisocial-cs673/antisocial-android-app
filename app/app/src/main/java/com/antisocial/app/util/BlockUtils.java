package com.antisocial.app.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BlockUtils {

    private static String currentMode = "All";

    public static void setCurrentMode(String mode)
    {
        currentMode = mode;
    }

    public static String getCurrentMode()
    {
        return currentMode;
    }

	/**
	 * @param instance
	 * @param serviceClass
	 */
	public static boolean isBlockServiceRunning( Activity instance, Class<?> serviceClass ){
		
		return isMyServiceRunning(instance, serviceClass);
	}

    /**
     *
      * @param instance
     * @param serviceClass
     * @return
     */
	private static boolean isMyServiceRunning(Activity instance, Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager) instance.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * @param context
	 * @return list of string of blocking apps
	 */
	public static ArrayList<String> getBlockList( Context context ){
		ArrayList<String> list = new ArrayList<String>();
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context); 
		Set<String> set = sharedPreferences.getStringSet(currentMode, null);

		if(set!=null){
			list =  new ArrayList<String>(set);
		}
		
		return list;
	}
	
	/**
	 * @param context
	 * @param list
	 * @return
	 */
	public static void save( Context context, ArrayList<String> list ){
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
         SharedPreferences.Editor editor= sharedPreferences.edit();

         Set<String> set = new HashSet<String>(list);

         editor.putStringSet(currentMode, set);

         editor.commit();
	}
}
