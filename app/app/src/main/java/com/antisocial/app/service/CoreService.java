package com.antisocial.app.service;

import java.util.ArrayList;
import java.util.Date;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import com.antisocial.app.WarningActivity;
import com.antisocial.app.util.ApiAdapter;
import com.antisocial.app.util.BlockUtils;
import com.antisocial.app.util.Logger;

public class CoreService extends Service {

	private static final int delayMillis = 1000;

	private ActivityManager mActivityManager;
	private static Date startedAt;
	private Handler mHandler;
	
	private ArrayList<String> mBlockList = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
	    mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	    
		mHandler = new Handler();
		mHandler.postDelayed(mRunnable, delayMillis);

		startedAt = new Date();

		Logger.getLogger().i( "onCreate" );
	}
	
	private Runnable mRunnable = new Runnable() {
		
		public void run() {

			Logger.getLogger().d( "block service is running..." );
			
			mBlockList = BlockUtils.getBlockList(getApplicationContext());

            String packageName = ApiAdapter.getPackageName(mActivityManager);

			if ( mBlockList!=null && mBlockList.contains(packageName) ) {
				
				Logger.getLogger().i( "block packageName：" + packageName );
				Logger.getLogger().i( "block className：" + packageName );
				
				Intent tancIntent = new Intent(getApplicationContext(), WarningActivity.class);
				tancIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(tancIntent);
			}
			
			mHandler.postDelayed(mRunnable,
					delayMillis);
		}
	};

	@Override
	public void onDestroy() {

		mHandler.removeCallbacks(mRunnable);
		super.onDestroy();
		
		Logger.getLogger().i( "onDestroy" );
	}

    public static Date getStartedAt() {
        return startedAt;
    }
}