package com.nit.plantsproject.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nit.plantsproject.Loginregister.LoginActivity;
import com.nit.plantsproject.Manager.SessionManager;
import com.nit.plantsproject.Profile.About;
import com.nit.plantsproject.Profile.ContactUs;
import com.nit.plantsproject.Profile.FAQ;
import com.nit.plantsproject.Profile.PrivacyPolicy;
import com.nit.plantsproject.R;

public class ProfileActivity extends AppCompatActivity {

    private Button about, contactUs, FAQ, policy, logout;
    private TextView username;
    private SessionManager sessionManager;
    private DatabaseReference databaseReference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        about = findViewById(R.id.btn_about_profile);
        contactUs = findViewById(R.id.btn_contact_profile);
        FAQ = findViewById(R.id.btn_faq_profile);
        policy = findViewById(R.id.btn_policy_profile);
        logout = findViewById(R.id.btn_logout_profile);
        username = findViewById(R.id.txt_username_profile);

        // Inisialisasi SessionManager
        sessionManager = new SessionManager(ProfileActivity.this);

        // Cek jika pengguna belum login, arahkan ke LoginActivity
        if (!sessionManager.isLoggedIn()) {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, About.class);
                startActivity(intent);
                finish();
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });

        FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FAQ.class);
                startActivity(intent);
                finish();
            }
        });

        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, PrivacyPolicy.class);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        // Mendapatkan instance Firebase Authentication
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        // Mendapatkan pengguna yang saat ini login
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // Mendapatkan referensi database
        databaseReference2 = FirebaseDatabase.getInstance().getReference("users");

        // Mendapatkan data pengguna yang saat ini login berdasarkan UID
        databaseReference2.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Mengambil nilai properti "name" dari snapshot
                    String name = dataSnapshot.child("name").getValue(String.class);
                    // Mengatur nilai nama pada TextView
                    username.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Menangani kesalahan yang terjadi saat mengambil data dari database
                Toast.makeText(ProfileActivity.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    // Metode untuk proses logout
    private void logout() {
        // Mendapatkan instance Firebase Authentication
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // Melakukan proses logout dari Firebase Authentication
        firebaseAuth.signOut();

        // Set status login menjadi false menggunakan SessionManager
        sessionManager.setLoggedIn(false);

        // Hapus Shared Preferences yang menyimpan data login
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();

        // Arahkan pengguna kembali ke LoginActivity
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);

        // Membersihkan semua history aktivitas
        finishAffinity();
        super.onBackPressed();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_notes:
                    startActivity(new Intent(getApplicationContext(), NotesActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_schedule:
                    startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    return true;
            }
            return false;
        });
    }
}