package com.bowazik.bob.ibet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bowazik.bob.ibet.activities.MenuActivity;
import com.bowazik.bob.ibet.interfaces.LoginInterfaces;
import com.bowazik.bob.ibet.presenter.LoginPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

public class LoginActivity extends AppCompatActivity implements LoginInterfaces.LoginView{

    private EditText editUserName;
    private EditText editPassword;
    private Button buttonLogin;
    private LoginPresenterImpl loginPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initPresenter();
        initView();
    }

    private void initView() {
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
    }

    private void initPresenter() {
        loginPresenterImpl = new LoginPresenterImpl(this);
    }

    @Override
    public void showErrorMessageForUsernamePassword() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_LOGIN, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessageForExceededAttempts() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_LOGIN, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoginSuccessMessage() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_SUCCESS_LOGIN, Toast.LENGTH_LONG).show();
        startLoginIntent();
    }

    private void startLoginIntent() {
        Intent loginIntent = new Intent(this, MenuActivity.class);
        startActivity(loginIntent);
    }
}
