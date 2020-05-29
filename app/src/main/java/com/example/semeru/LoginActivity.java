package com.example.semeru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout linearLayoutSignup;
    private LinearLayout linearLayoutLogin;
    private TextView textViewSignup;
    private TextView textViewLogin;
    private ViewGroup.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linearLayoutLogin = (LinearLayout) findViewById(R.id.linearLayoutLogin);
        linearLayoutSignup = (LinearLayout) findViewById(R.id.linearLayoutSignup);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutParams = linearLayoutLogin.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                linearLayoutLogin.setLayoutParams(layoutParams);

                layoutParams = linearLayoutSignup.getLayoutParams();
                layoutParams.height = 0;
                linearLayoutSignup.setLayoutParams(layoutParams);
            }
        });

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutParams = linearLayoutSignup.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                linearLayoutSignup.setLayoutParams(layoutParams);

                layoutParams = linearLayoutLogin.getLayoutParams();
                layoutParams.height = 0;
                linearLayoutLogin.setLayoutParams(layoutParams);
            }
        });
    }
}