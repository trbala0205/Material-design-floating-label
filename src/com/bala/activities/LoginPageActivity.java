package com.bala.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPageActivity extends AppCompatActivity{

	private Toolbar toolbar;
	private EditText txt_UserName, txt_Email, txt_Password;
    private TextInputLayout inputUserNameLayout, inputEmailLayout, inputPasswordLayout;
    private Button btnSignIn;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        inputUserNameLayout = (TextInputLayout) findViewById(R.id.input_username_layout);
        inputEmailLayout = (TextInputLayout) findViewById(R.id.input_email_layout);
        inputPasswordLayout = (TextInputLayout) findViewById(R.id.input_password_layout);
        txt_UserName = (EditText) findViewById(R.id.input_username);
        txt_Email = (EditText) findViewById(R.id.input_email);
        txt_Password = (EditText) findViewById(R.id.input_password);
        btnSignIn = (Button) findViewById(R.id.btn_signup);
        
        txt_UserName.addTextChangedListener(new MyTextWatcher(txt_UserName));
        txt_Email.addTextChangedListener(new MyTextWatcher(txt_Email));
        txt_Password.addTextChangedListener(new MyTextWatcher(txt_Password));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitSignInForm();
            }
        });
	}
	
	   private class MyTextWatcher implements TextWatcher {

	        private View view;

	        private MyTextWatcher(View view) {
	            this.view = view;
	        }

	        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	        }

	        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	        }

			@Override
			public void afterTextChanged(Editable s) {
				switch (view.getId()) {
	                case R.id.input_username:
	                    validateName();
	                    break;
	                case R.id.input_email:
	                    validateEmail();
	                    break;
	                case R.id.input_password:
	                    validatePassword();
	                    break;
				}
			}
	   }
			
		/**
	     * Validating form
	     */
	    private void submitSignInForm() {
	        if (!validateName()) {
	            return;
	        }

	        if (!validateEmail()) {
	            return;
	        }

	        if (!validatePassword()) {
	            return;
	        }

	        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
	    }

	    private boolean validateName() {
	        if (txt_UserName.getText().toString().trim().isEmpty()) {
	        	inputUserNameLayout.setError(getString(R.string.err_msg_name));
	            requestFocus(txt_UserName);
	            return false;
	        } else {
	        	inputUserNameLayout.setErrorEnabled(false);
	        }

	        return true;
	    }

	    private boolean validateEmail() {
	        String email = txt_Email.getText().toString().trim();

	        if (email.isEmpty() || !isValidEmail(email)) {
	        	inputEmailLayout.setError(getString(R.string.err_msg_email));
	            requestFocus(txt_Email);
	            return false;
	        } else {
	        	inputEmailLayout.setErrorEnabled(false);
	        }

	        return true;
	    }

	    private boolean validatePassword() {
	        if (txt_Password.getText().toString().trim().isEmpty()) {
	        	inputPasswordLayout.setError(getString(R.string.err_msg_password));
	            requestFocus(txt_Password);
	            return false;
	        } else {
	        	inputPasswordLayout.setErrorEnabled(false);
	        }

	        return true;
	    }
	    
	    private boolean isValidEmail(String email) {
	        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	    }

	    private void requestFocus(View view) {
	        if (view.requestFocus()) {
	            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
	        }
	    }
}
