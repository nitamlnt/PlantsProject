package com.nit.plantsproject.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nit.plantsproject.Main.ProfileActivity;
import com.nit.plantsproject.R;

public class ContactUs extends AppCompatActivity {

    ImageView arrowLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        arrowLeft = findViewById(R.id.arrow_left);

        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Membersihkan semua history aktivitas
        Intent intent = new Intent(ContactUs.this, ProfileActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}