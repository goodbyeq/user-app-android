package com.beatus.selfkart.screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beatus.selfkart.R;

public class Login extends AppCompatActivity implements View.OnClickListener {


    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {
            Intent intent = new Intent(Login.this, StoreSearch.class);
            startActivity(intent);
            finish();
        }
    }

}
