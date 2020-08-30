package com.example.sensor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class Recaptcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    String SiteKey = "6LcsZsQZAAAAAEJ8X7hll9tm0EksKMz76v_Fbmdv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaptcha);

        checkBox = findViewById(R.id.checkbox);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Recaptcha.this)
                .build();
        googleApiClient.connect();
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if ((status != null) && status.isSuccess()) {
                                        Toast.makeText(getApplicationContext(), "Successfully Varified..."
                                                , Toast.LENGTH_SHORT).show();
                                        Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                                        shareIntent.setType("text/plain");
                                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                                        String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                                        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                                    }
                                }

                            });
                } else {
                    // Default checkbox text color
                    checkBox.setTextColor(Color.RED);
                    Toast.makeText(Recaptcha.this, "You are a Robot...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}