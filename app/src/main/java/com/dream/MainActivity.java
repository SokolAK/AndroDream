package com.dream;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;

import com.dream.services.InsomniaTile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.android.systemui", "com.android.systemui.Somnambulator");
        startActivity(intent);


        finishAffinity();
    }
}
