package com.acomputerengineer;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class CircleImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_view);

        ImageView ivR = findViewById(R.id.iv_r);
        ShapeDrawable sdR = new ShapeDrawable(new OvalShape());
        sdR.setIntrinsicHeight(100);
        sdR.setIntrinsicWidth(100);
        sdR.getPaint().setColor(Color.parseColor("#ff0000"));
        ivR.setImageDrawable(sdR);

        ImageView ivG = findViewById(R.id.iv_g);
        ShapeDrawable sdG = new ShapeDrawable(new OvalShape());
        sdG.setIntrinsicHeight(100);
        sdG.setIntrinsicWidth(100);
        sdG.getPaint().setColor(Color.parseColor("#00ff00"));
        ivG.setImageDrawable(sdG);

        ImageView ivB = findViewById(R.id.iv_b);
        ShapeDrawable sdB = new ShapeDrawable(new OvalShape());
        sdB.setIntrinsicHeight(100);
        sdB.setIntrinsicWidth(100);
        sdB.getPaint().setColor(Color.parseColor("#0000ff"));
        ivB.setImageDrawable(sdB);
    }
}
