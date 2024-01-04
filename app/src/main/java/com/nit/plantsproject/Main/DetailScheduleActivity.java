package com.nit.plantsproject.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nit.plantsproject.R;

public class DetailScheduleActivity extends AppCompatActivity {

    TextView detailSchedule, detailDay, detailGuest, detailTime;
    ImageView detailImg;
    FloatingActionButton deleteButton1, editButton1;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule);

        detailSchedule = findViewById(R.id.detailSchedule);
        detailDay = findViewById(R.id.detailDay);
        detailGuest = findViewById(R.id.detailGuest);
        detailTime = findViewById(R.id.detailTime);
        detailImg = findViewById(R.id.detailImg);
        deleteButton1 = findViewById(R.id.deleteButton1);
        editButton1 = findViewById(R.id.editButton1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailSchedule.setText(bundle.getString("Schedule"));
            detailDay.setText(bundle.getString("Day"));
            detailGuest.setText(bundle.getString("Guest"));
            detailTime.setText(bundle.getString("Time"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImg);
        }
        deleteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Schedule");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailScheduleActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
                        finish();
                    }
                });
            }
        });
        editButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailScheduleActivity.this, UpdateScheduleActivity.class)
                        .putExtra("Schedule", detailSchedule.getText().toString())
                        .putExtra("Day", detailDay.getText().toString())
                        .putExtra("Guest", detailGuest.getText().toString())
                        .putExtra("Time", detailTime.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}