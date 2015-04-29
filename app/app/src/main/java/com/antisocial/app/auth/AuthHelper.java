package com.antisocial.app.auth;

import java.util.Date;

import com.antisocial.app.webapi.domain.AuthToken;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthHelper {

	public final static String AUTH_KEY = "antisocial.auth";
	
	public final static String AUTH_KEY_TOKEN = "auth.token";
	public final static String AUTH_KEY_TOKEN_TYPE = "auth.token_type";
	public final static String AUTH_KEY_TOKEN_EXPIRES = "auth.expires";
	public final static String AUTH_KEY_TOKEN_EXPIRES_IN = "auth.expires_in";
	public final static String AUTH_KEY_TOKEN_USERNAME = "auth.userName";
	public final static String AUTH_KEY_TOKEN_ISSUED = "auth.issued";
	
	public static AuthToken getAuthToken(Context context) {
		
		SharedPreferences settings = context.getSharedPreferences(AUTH_KEY, Context.MODE_PRIVATE);
		AuthToken token = new AuthToken();
		token.setAccessToken(settings.getString(AUTH_KEY_TOKEN, null));
		token.setExpires(new Date(settings.getLong(AUTH_KEY_TOKEN_EXPIRES, 0)));
		token.setExpiresIn(settings.getInt(AUTH_KEY_TOKEN_EXPIRES_IN, 0));
		token.setIssued(new Date(settings.getLong(AUTH_KEY_TOKEN_ISSUED, 0)));
		token.setTokenType(settings.getString(AUTH_KEY_TOKEN_TYPE, null));
		token.setUserName(settings.getString(AUTH_KEY_TOKEN_USERNAME, null));
		
		return token;
	}
	
	public static void setAuthToken(Context context, AuthToken token) {
		
		SharedPreferences settings = context.getSharedPreferences(AUTH_KEY, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(AUTH_KEY_TOKEN, token.getAccessToken());
		editor.putInt(AUTH_KEY_TOKEN_EXPIRES_IN, token.getExpiresIn());
		editor.putString(AUTH_KEY_TOKEN_TYPE, token.getTokenType());
		editor.putString(AUTH_KEY_TOKEN_USERNAME, token.getUserName());
		editor.putLong(AUTH_KEY_TOKEN_EXPIRES, token.getExpires().getTime());
		editor.putLong(AUTH_KEY_TOKEN_ISSUED, token.getIssued().getTime());
		editor.commit();
	}
	
	public static void removeAuthToken(Context context) {
		SharedPreferences settings = context.getSharedPreferences(AUTH_KEY, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.clear();
		editor.commit();
	}
	
	public static boolean isEmailValid(String email) {
		
		return email.contains("@");
	}
	
	public static boolean isPasswordValid(String password) {
		// TODO: Replace this with your own logic
		return password.length() > 6;
	}
}
