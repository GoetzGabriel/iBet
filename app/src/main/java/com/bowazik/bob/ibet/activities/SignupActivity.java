package com.bowazik.bob.ibet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.interfaces.SignupInterfaces;
import com.bowazik.bob.ibet.presenter.SignupPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * Created by bob on 17.07.17.
 */

public class SignupActivity extends AppCompatActivity implements SignupInterfaces.SignupView{

    private EditText editUsername;
    private EditText editPassword;
    private Button btnSignup;
    private SignupPresenterImpl signupPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_signup);

        initPresenter();
        initView();
    }

    private void initView() {
        editUsername = (EditText) findViewById(R.id.signup_input_name);
        editPassword = (EditText) findViewById(R.id.signup_input_password);
        btnSignup = (Button) findViewById(R.id.btn_login);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupPresenterImpl.signUp(editUsername.getText().toString().trim(),
                        editPassword.getText().toString().trim());
            }
        });
    }

    private void initPresenter() {
        signupPresenterImpl = new SignupPresenterImpl(this);
    }

    @Override
    public void showErrorMessageUsernameTaken() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SIGN_UP_NAME, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessageInvalidInput() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_SIGN_UP_INPUT, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSignupSuccessMessage() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_SUCCESS_SIGN_UP, Toast.LENGTH_LONG).show();
    }
}
