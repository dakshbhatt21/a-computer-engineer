package com.acomputerengineer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.acomputerengineer.CustomView.DrawLineCanvas;

public class DrawLineWithFingerActivity extends AppCompatActivity {

    private DrawLineCanvas dlc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_line_with_finger);

        dlc = findViewById(R.id.dlc);


    }


}
