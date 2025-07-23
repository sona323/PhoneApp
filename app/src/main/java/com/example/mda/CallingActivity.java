package com.example.mda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallingActivity extends AppCompatActivity {

    TextView txtCallingNumber;
    ImageButton btnCutCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);

        txtCallingNumber = findViewById(R.id.txtCallingNumber);
        btnCutCall = findViewById(R.id.btnCutCall);

        String phoneNumber = getIntent().getStringExtra("phone");

        txtCallingNumber.setText("Calling " + phoneNumber + "...");

        // Save to Call Log
        CallLogEntity callLog = new CallLogEntity();
        callLog.name = ""; // you can add logic to fetch contact name
        callLog.phone = phoneNumber;
        callLog.callType = "OUTGOING";
        callLog.callTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());

        AppDatabase.getInstance(this).callLogDao().insertCallLog(callLog);

        btnCutCall.setOnClickListener(v -> {
            finish();  // Close this screen on Cut
        });
    }
}
