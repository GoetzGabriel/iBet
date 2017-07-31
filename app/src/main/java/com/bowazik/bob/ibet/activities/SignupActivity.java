package com.bowazik.bob.ibet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bowazik.bob.ibet.LoginActivity;
import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.presenter.SignupPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The sign up activity of the app.
 * The user can enter an email and a password and create a new account.
 * The user data is sent to and verified by the web server.
 * If the entered data is valid a new account is created and a success message is shown.
 * Otherwise an error alert is shown.
 * The user can click a hint which redirects to the LoginActivity.
 */

public class SignupActivity extends AppCompatActivity implements SignupInterfaces.SignupView{

    private TextView loginLink;
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSignup;
    private SignupPresenterImpl signupPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initPresenter();
        initView();
    }

    private void initView() {
        loginLink = (TextView) findViewById(R.id.link_login);
        editUsername = (EditText) findViewById(R.id.signup_input_name);
        editPassword = (EditText) findViewById(R.id.signup_input_password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupPresenterImpl.signUp(editUsername.getText().toString().trim(),
                        editPassword.getText().toString().trim());
            }
        });
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLoginActivity();
            }
        });
    }

    //Use an intent to start the login activity
    private void switchToLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    //Initiate the sign up presenter
    private void initPresenter() {
        signupPresenterImpl = new SignupPresenterImpl(this);
    }

    //Show an error message if the chosen username(email) is already taken
    @Override
    public void showErrorMessageUsernameTaken() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SIGN_UP_NAME, Toast.LENGTH_LONG).show();
    }

    //Show an error message if the user input was not valid(e.g. empty)
    @Override
    public void showErrorMessageInvalidInput() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SIGN_UP_INPUT, Toast.LENGTH_LONG).show();
    }

    //Show an success message if the account has been created successfully
    @Override
    public void showSignupSuccessMessage() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_SUCCESS_SIGN_UP, Toast.LENGTH_LONG).show();
    }

    //Show an error message if an server error occurred
    @Override
    public void showErrorMessageServer() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SIGN_UP_SERVER, Toast.LENGTH_LONG).show();
    }
}
