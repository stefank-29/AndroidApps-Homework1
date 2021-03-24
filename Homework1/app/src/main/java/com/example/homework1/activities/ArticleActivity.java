package com.example.homework1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homework1.R;

public class ArticleActivity extends AppCompatActivity {

    // View comps
    private TextView welcomeMsg;
    private TextView articleText;
    private Button favoriteBtn;
    private ImageView star;


    // Codes
    public static final String USERNAME_KEY = "username";
    public static final String FAVORITE_BTN_TEXT = "btnText";
    public static final String STAR_ICON = "starSrc";


    // Props
    private String username;
    public static String btnText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        init();
    }

    public void init() {
        initView();
        initListeners();
        parseIntent();
        initWelcomeMessage();
        initBtnAndStar();
    }

    public void initView() {
        welcomeMsg = findViewById(R.id.welcomeTv);
        articleText = findViewById(R.id.articleTv);
        favoriteBtn = findViewById(R.id.favoriteBtn);
        star = findViewById(R.id.starIcon);

        articleText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ante ante, semper sit amet consectetur ac, rutrum ut risus. Cras fermentum felis at metus cursus, vel accumsan lectus feugiat. Vestibulum eget est eget turpis blandit luctus. Quisque feugiat facilisis sollicitudin. Nulla varius lacinia ipsum ac consequat. Maecenas pharetra scelerisque ex, et interdum arcu malesuada vitae. Donec sit amet odio euismod mauris laoreet maximus.\n" +
                "\n" +
                "Nullam in ante ac enim rhoncus facilisis sit amet non massa. In volutpat nisi a sagittis tempor. Sed ante arcu, sagittis id lectus eget, sollicitudin molestie nunc. Duis blandit malesuada augue a pretium. Quisque a tellus vel est aliquam blandit. Sed interdum enim vel porttitor ullamcorper. Suspendisse non posuere orci. Curabitur pellentesque efficitur nibh suscipit efficitur. Mauris euismod arcu sed sem vestibulum pretium. Sed tincidunt elit mollis massa gravida sollicitudin. Donec justo risus, suscipit eu massa tristique, suscipit ullamcorper ex. Nullam sed turpis non lorem porttitor accumsan sit amet eu purus. Nullam at commodo sem. Duis sit amet lectus placerat, faucibus orci in, iaculis nulla. Vestibulum ornare lectus sit amet massa iaculis maximus.\n" +
                "\n" +
                "Sed mattis lobortis finibus. Nunc varius justo orci, id scelerisque urna ornare quis. Quisque et efficitur est. Curabitur odio arcu, eleifend sed rutrum a, finibus in sem. Cras dui dui, faucibus at faucibus et, accumsan vel dolor. Cras nec libero vel purus condimentum faucibus ac eget augue. Sed ut sollicitudin velit. Maecenas leo risus, sodales sed est at, iaculis semper tortor.\n" +
                "\n" +
                "Sed ac erat vitae sapien euismod dignissim in tincidunt tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam turpis tellus, elementum in faucibus nec, placerat feugiat mi. Integer pellentesque lobortis nisl, at dictum arcu rhoncus vel. Phasellus ante nisl, vestibulum ut neque in, aliquam vulputate diam. Maecenas dolor augue, congue eu dolor sit amet, vulputate eleifend ex. Proin ac faucibus ex. Suspendisse rutrum odio sit amet commodo tempor. Donec ac mi felis. Cras bibendum non dolor vitae lacinia. Pellentesque venenatis bibendum felis, vel sagittis purus varius in. Maecenas ultricies, justo eget tempor elementum, nibh arcu hendrerit ante, commodo congue ipsum risus ac mauris.");
    }

    public void initListeners() {
        favoriteBtn.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            String text = sharedPreferences.getString(FAVORITE_BTN_TEXT, "");
            if (text.equals("Favorite")) {
                text = "Unfavorite";
                star.setImageResource(R.drawable.ic_baseline_star_50);
            } else if (text.equals("Unfavorite")) {
                text = "Favorite";
                star.setImageResource(R.drawable.ic_baseline_star_border_50);
            }
            sharedPreferences
                    .edit()
                    .putString(FAVORITE_BTN_TEXT, text)
                    .apply();
            favoriteBtn.setText(text);
        });
    }

    public void parseIntent() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) { // prelazim sa login activitija
            username = intent.getExtras().getString(USERNAME_KEY);
        } else {  // direktno sa splash screena
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            username = sharedPreferences.getString(LoginActivity.LOGIN_USERNAME, null);
        }
    }

    public void initWelcomeMessage() {
        welcomeMsg.setText("Welcome " + username + " here's an article of the day, do you like it?");
    }

    public void initBtnAndStar() {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        String btnText = sharedPreferences.getString(FAVORITE_BTN_TEXT, null);
        if (btnText == null) { // prvi put
            sharedPreferences
                    .edit()
                    .putString(FAVORITE_BTN_TEXT, "Favorite")
                    .apply();
            sharedPreferences
                    .edit()
                    .putString(STAR_ICON, "ic_baseline_star_border_50")
                    .apply();
            favoriteBtn.setText("Favorite");
            star.setImageResource(R.drawable.ic_baseline_star_border_50);
        } else {
            favoriteBtn.setText(btnText);
            if (btnText.equals("Favorite")) {
                star.setImageResource(R.drawable.ic_baseline_star_border_50);
            } else {
                star.setImageResource(R.drawable.ic_baseline_star_50);

            }
        }

    }

}