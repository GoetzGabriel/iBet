package com.bowazik.bob.ibet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bowazik.bob.ibet.R;
import com.bowazik.bob.ibet.interfaces.NewBetInterfaces;
import com.bowazik.bob.ibet.presenter.NewBetPresenterImpl;
import com.bowazik.bob.ibet.utility.Constants;

/**
 * The new bet activity of the app.
 * The user can create a new bet consisting of a title, a description, a value, and a contender.
 * The entered data is sent to the web server and validated.
 * If the entered data is valid a new bet is created and a success message is shown.
 * Otherwise an error alert is shown.
 */

public class NewBetActivity extends AppCompatActivity implements NewBetInterfaces.NewBetView {

    private EditText editContender, editTitle, editDescription, editValue;
    private Button btnSubmit;
    private NewBetPresenterImpl newBetPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bet);

        initPresenter();
        initView();
    }

    private void initView() {
        editTitle = (EditText) findViewById(R.id.input_bet_title);
        editDescription = (EditText) findViewById(R.id.input_bet_desc);
        editContender = (EditText) findViewById(R.id.input_bet_contender);
        editValue = (EditText) findViewById(R.id.input_bet_value);
        btnSubmit = (Button) findViewById(R.id.btn_submit_bet);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBetPresenterImpl.doSubmitBet(editTitle.getText().toString().trim(),
                        editDescription.getText().toString().trim(),
                        editContender.getText().toString().trim(),
                        editValue.getText().toString().trim());
            }
        });
    }

    //Initiate the new bet presenter
    private void initPresenter() {
        newBetPresenterImpl = new NewBetPresenterImpl(this, this);
    }

    //SHow an error message if the bet could not be created successfully
    @Override
    public void showErrorMessageForContenderBet() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_NEW_BET, Toast.LENGTH_LONG).show();
    }

    //Show a success message if the bet has been created successfully
    @Override
    public void showSuccessMessageForContenderBet() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_SUCCESS_NEW_BET, Toast.LENGTH_LONG).show();
        switchToMenuActivity();
    }

    //Switch to the main menu after creating a new bet
    private void switchToMenuActivity() {
        Intent startMenuActivity = new Intent(this, MenuActivity.class);
        startActivity(startMenuActivity);
    }
}
