package com.antisocial.app;

import java.util.List;
import java.util.Map;

import com.antisocial.app.auth.AuthHelper;
import com.antisocial.app.util.AntisocialUtils;
import com.antisocial.app.webapi.communication.requests.PostRegisterRequest;
import com.antisocial.app.webapi.domain.RequestState;
import com.antisocial.app.webapi.domain.UserRegisterInfo;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.domain.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	// UI references.
	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private EditText mPasswordConfirmView;
	private View mProgressView;
	private View mLoginFormView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordConfirmView = (EditText)findViewById(R.id.register_confirm_password);
		mPasswordConfirmView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.register_button || id == EditorInfo.IME_NULL) {
							atemptRegister();
							return true;
						}
						return false;
					}
				});

			
		Button registerButton = (Button) findViewById(R.id.register_button);
		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				atemptRegister();
			}
		});
		
		mLoginFormView = findViewById(R.id.register_form);
		mProgressView = findViewById(R.id.register_progress);
	}
	
	private void atemptRegister() {
		// Reset errors.
		
		if(!AntisocialUtils.isInternetConnected(this)) {
			getCurrentFocus().clearFocus();
			Toast.makeText(this, R.string.message_no_internet_connection_check, Toast.LENGTH_LONG).show();
			return;
		}
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String email = mEmailView.getText().toString();
		String password = mPasswordView.getText().toString();
		String confirmPassword = mPasswordConfirmView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !AuthHelper.isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}
		
		// Check for an equality password and confirm password.
		if (!TextUtils.equals(password, confirmPassword)){
			mPasswordConfirmView.setError(getString(R.string.error_password_and_confirm_password_do_not_match));
			focusView = mPasswordConfirmView;
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
			showProgress(true);
			
			final Activity launchActivity = this;
			
			UserRegisterInfo user = new UserRegisterInfo(email, password, confirmPassword);

            try {
                new PostRegisterRequest(this, user, new HttpCallback<RequestState>() {

                    @Override
                    public void onHttpError(ResponseStatus responseStatus) {
                        showProgress(false);
                        mPasswordView
                        .setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                    }

                    @Override
                    public void onSuccess(RequestState requestState, ResponseStatus responseStatus) {

                        if(requestState == null)
                        {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //intent.putExtra("cards", new ArrayList<Card>(responseData));
                            startActivity(intent);

                            launchActivity.finish();
                        } else {
                            Map<String, List<String>> modelState = requestState.getModelState();
                            for (String key : modelState.keySet()) {
                                String errorMessages = TextUtils.join("", modelState.get(key));
                                if (key == "Password") {
                                    mPasswordView.setError(errorMessages);
                                    mPasswordView.requestFocus();
                                } else {
                                    mPasswordView.setError(errorMessages);
                                    mPasswordView.requestFocus();
                                }
                            }
                        }

                        showProgress(false);
                    }
                }).executeAsync();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
	}
	
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mProgressView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
}
