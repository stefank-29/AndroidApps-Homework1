package com.example.homework1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.homework1.R;

import java.util.concurrent.ThreadLocalRandom;

public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_USERNAME = "username";
    public static final String LOGIN_PASSWORD = "password";


    // View comps
    private ImageView imageView;
    private Button loginBtn;
    private EditText usernameEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init() {
        initView();
        initGlide();
        initListeners();
    }

    public void initView() {
        imageView = findViewById(R.id.loginIv);
        loginBtn = findViewById(R.id.loginBtn);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);

    }

    public void initListeners() {
        loginBtn.setOnClickListener(v -> {
            String username = usernameEt.getText().toString();
            // zapamtim username i password
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences
                    .edit()
                    .putString(LOGIN_USERNAME, username)
                    .apply();
            sharedPreferences
                    .edit()
                    .putString(LOGIN_PASSWORD, passwordEt.getText().toString())
                    .apply();
            // saljem username kao intent nakon login-a
            Intent intent = new Intent(this, ArticleActivity.class);
            intent.putExtra(ArticleActivity.USERNAME_KEY, username);
            startActivity(intent);
            finish();

        });

    }

    public void initGlide() {
        String[] gifs = {"https://media.tenor.com/images/6c128f5edeb30e4bdf91c7a9da3cfdec/tenor.gif", "https://media1.tenor.com/images/c0c2264911d8cd4a688acd0542240f95/tenor.gif", "https://i.pinimg.com/originals/4b/92/a7/4b92a7fcfc1493a0e35eecf82cd1875f.gif"};
        Glide.with(this).load(gifs[ThreadLocalRandom.current().nextInt(0, gifs.length)]).into(imageView);
    }


}