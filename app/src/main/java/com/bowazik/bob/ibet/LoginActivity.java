package com.bowazik.bob.ibet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bowazik.bob.ibet.activities.MenuActivity;
import com.bowazik.bob.ibet.activities.SignupActivity;
import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.presenter.LoginPresenterImpl;
import com.bowazik.bob.ibet.sharedPrefs.IbetSharedPrefs;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The login activity of the app.
 * The user can enter an email and a password and log in which starts the validation process.
 * The user data is sent to and verified by the web server.
 * If the entered data is valid the user is redirected to the MenuActivity.
 * Otherwise an error alert is shown.
 * The user can click a hint which redirects to the SignupAcitivity.
 */

public class LoginActivity extends AppCompatActivity implements LoginInterfaces.LoginView{

    private EditText editUserName, editPassword;
    private Button buttonLogin;
    private LoginPresenterImpl loginPresenterImpl;
    private TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initPresenter();
        initView();
    }

    private void initView() {
        signupLink = (TextView) findViewById(R.id.link_signup);
        editUserName = (EditText) findViewById(R.id.input_email);
        editPassword = (EditText) findViewById(R.id.input_password);
        buttonLogin = (Button) findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterImpl.doLogin(editUserName.getText().toString().trim(),
                        editPassword.getText().toString().trim());
            }
        });
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToSignupActivity();
            }
        });
    }

    //Use an intent to start the sign up activity
    private void switchToSignupActivity() {
        Intent signupIntent = new Intent(this, SignupActivity.class);
        startActivity(signupIntent);
    }

    //Initiate the login presenter
    private void initPresenter() {
        loginPresenterImpl = new LoginPresenterImpl(this);
    }

    //Show an error alert if the entered user data is not valid
    @Override
    public void showErrorMessageForUsernamePassword() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_LOGIN, Toast.LENGTH_LONG).show();
    }

    //Show an error alert if the number of failed user login attempts exceeds the allowed number
    @Override
    public void showErrorMessageForExceededAttempts() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_LOGIN, Toast.LENGTH_LONG).show();
    }

    /**
     * Show a success message if the entered user data is valid.
     * Save the id of the user returned by the web server using the shared preferences.
     * @param userId Id of the active user, returned by the web server
     */
    @Override
    public void showLoginSuccessMessage(int userId) {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_SUCCESS_LOGIN, Toast.LENGTH_LONG).show();
        IbetSharedPrefs ibetSharedPrefs = new IbetSharedPrefs(this);
        ibetSharedPrefs.saveUserId(userId);
        startLoginIntent();
    }

    //Use an intent to start the login activity
    private void startLoginIntent() {
        Intent loginIntent = new Intent(this, MenuActivity.class);
        startActivity(loginIntent);
    }
}
