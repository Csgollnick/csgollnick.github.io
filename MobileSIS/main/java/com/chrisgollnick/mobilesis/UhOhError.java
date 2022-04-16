package com.chrisgollnick.mobilesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UhOhError extends AppCompatActivity {
    private Button startover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uh_oh_error);

        startover = (Button)findViewById(R.id.startoverButton);

        startover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UhOhError.this, MainActivity.class));
            }
        });
    }
}