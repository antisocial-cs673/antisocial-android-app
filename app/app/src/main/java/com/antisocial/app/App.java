package com.antisocial.app;

import com.antisocial.app.auth.AntisocialAuthenticationProvider;
//import com.antisocial.app.util.TypefaceUtil;

import android.app.Application;
import android.content.Context;

import com.dg.libs.rest.RestClientConfiguration;

public class App extends Application {

	private static Context context;
	public static Context getAppContext() {
		return context;
	}
	
	@Override
	public void onCreate() {

		super.onCreate();
		
		context = getApplicationContext();

        RestClientConfiguration builder = new RestClientConfiguration.ConfigurationBuilder()
                .setAuthenticationProvider(new AntisocialAuthenticationProvider(this))
                .create();

        RestClientConfiguration.init(this, builder);

        //TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf");

    }

}
