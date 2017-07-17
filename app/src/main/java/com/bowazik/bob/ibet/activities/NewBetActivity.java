package com.bowazik.bob.ibet.activities;

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

public class NewBetActivity extends AppCompatActivity implements NewBetInterfaces.NewBetView {

    private EditText editContender;
    private EditText editBet;
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
        editContender = (EditText) findViewById(R.id.input_bet_contender);
        editBet = (EditText) findViewById(R.id.input_bet_desc);
        btnSubmit = (Button) findViewById(R.id.btn_submit_bet);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBetPresenterImpl.doSubmitBet(editContender.getText().toString().trim(),
                        editBet.getText().toString().trim());
            }
        });
    }

    private void initPresenter() {
        newBetPresenterImpl = new NewBetPresenterImpl(this, this);
    }

    @Override
    public void showErrorMessageForContenderBet() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_ERROR_NEW_BET, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessageForContenderBet() {
        Toast.makeText(getBaseContext(), Constants.MESSAGE_SUCCESS_NEW_BET, Toast.LENGTH_LONG).show();
    }
}
