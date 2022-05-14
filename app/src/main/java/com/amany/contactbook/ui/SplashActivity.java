package com.amany.contactbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.amany.contactbook.R;
import com.amany.contactbook.databinding.ActivitySplashBinding;
import com.amany.contactbook.ui.ContactListActivity;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnGetStarted.setOnClickListener(view -> {
            Intent intent = new Intent(this, ContactListActivity.class);
            startActivity(intent);
            finish();
        });

    }
}