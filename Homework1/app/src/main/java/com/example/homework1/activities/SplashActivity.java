package com.example.homework1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(LoginActivity.LOGIN_USERNAME, null);
        String password = sharedPreferences.getString(LoginActivity.LOGIN_PASSWORD, null);
        Intent intent;
        if (username != null && password != null) {
            intent = new Intent(this, ArticleActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);

        }
        startActivity(intent);
        finish();
    }
}