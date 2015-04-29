package com.antisocial.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.antisocial.app.auth.AuthHelper;
import com.antisocial.app.util.AntisocialUtils;
import com.antisocial.app.util.ProgressBarUtils;
import com.antisocial.app.webapi.communication.requests.GetAuthTokenRequest;
import com.antisocial.app.webapi.domain.AuthToken;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.domain.ResponseStatus;

/**
 * A login screen that offers login via email/password.
 */
@SuppressLint("NewApi")
public class LoginActivity extends Activity {

	// UI references.
	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private View mProgressView;
	private ScrollView mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mLoginFormView = (ScrollView)findViewById(R.id.register_form);
		mProgressView = findViewById(R.id.register_progress);

		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		mEmailSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});
		
		Button registerButton = (Button) findViewById(R.id.register_button);
		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(intent);
			}
		});

	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		
		if(!AntisocialUtils.isInternetConnected(this)) {
			getCurrentFocus().clearFocus();
			Toast.makeText(this, R.string.message_no_internet_connection_check, Toast.LENGTH_LONG).show();
			return;
		}
		
		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String email = mEmailView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !AuthHelper.isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(email)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!AuthHelper.isEmailValid(email)) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			AntisocialUtils.hideSoftKeyboard(this);
			final ProgressDialog progress = ProgressBarUtils.showProgressDialog(this, R.string.title_loading_login, R.string.title_loading_login);
			
			final Activity launchActivity = this;
			
			new GetAuthTokenRequest(this, email, password, new HttpCallback<AuthToken>() {
				
				@Override
				public void onHttpError(ResponseStatus responseStatus) {
					progress.dismiss();
					mPasswordView
					.setError(getString(R.string.error_incorrect_password));
					//mPasswordView.requestFocus();
					AntisocialUtils.showSoftKeyboard(mPasswordView);
				}
	
				@Override
				public void onSuccess(AuthToken responseData, ResponseStatus responseStatus) {

					AuthHelper.setAuthToken(launchActivity, responseData);
					
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					//intent.putExtra("cards", new ArrayList<Card>(responseData));
                    launchActivity.finish();
                    startActivity(intent);
					//progress.dismiss();
					
				}
			}).executeAsync();
			
		}
	}	
}
