package com.acomputerengineer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

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
