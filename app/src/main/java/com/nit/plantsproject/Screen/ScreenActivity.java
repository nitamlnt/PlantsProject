package com.nit.plantsproject.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nit.plantsproject.Loginregister.LoginActivity;
import com.nit.plantsproject.R;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
    }

    public void getStarted(View v) {
        Intent intent = new Intent(ScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}