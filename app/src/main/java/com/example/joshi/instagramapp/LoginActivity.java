package com.example.joshi.instagramapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Instagram_APP";
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        // TODO: navigate to new activity if the user has signed properly
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with login");
                    e.printStackTrace();
                    return;
                }

                if (user != null) {
                    // Hooray! The user is logged in.
                    Log.e(TAG, "User is logged in");
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    if (currentUser != null) {
                        goMainActivity();
                    } else {
                        //stay on login
                    }
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    e.printStackTrace();
                }
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Log.d(TAG, "Navigating to Main Activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
