package com.antisocial.app.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BlockUtils {


	
	/**
	 * Blocking application list
	 * 
	 * @param context
	 * @return
	 */
	public static ArrayList<String> getBlockList( Context context ){
		ArrayList<String> list = new ArrayList<String>();
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context); 
		Set<String> set = sharedPreferences.getStringSet("applist", null);
		if(set!=null){
			list =  new ArrayList<String>(set);
		}
		
		return list;
	}
	
	/**
	 * 存储block列表
	 * 
	 * @param context
	 * @param list
	 * @return
	 */
	public static void save( Context context, ArrayList<String> list ){
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);  
		 SharedPreferences.Editor editor= sharedPreferences.edit();  
		 
		 Set<String> set = new HashSet<String>(list);
		 
		 editor.putStringSet("applist", set);
		 
		 editor.commit();
	}
}
