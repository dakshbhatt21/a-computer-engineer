package com.acomputerengineer;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CircleImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_view);

        ImageView ivR = (ImageView) findViewById(R.id.iv_r);
        ShapeDrawable sdR = new ShapeDrawable(new OvalShape());
        sdR.setIntrinsicHeight(100);
        sdR.setIntrinsicWidth(100);
        sdR.getPaint().setColor(Color.parseColor("#ff0000"));
        ivR.setImageDrawable(sdR);

        ImageView ivG = (ImageView) findViewById(R.id.iv_g);
        ShapeDrawable sdG = new ShapeDrawable(new OvalShape());
        sdG.setIntrinsicHeight(100);
        sdG.setIntrinsicWidth(100);
        sdG.getPaint().setColor(Color.parseColor("#00ff00"));
        ivG.setImageDrawable(sdG);

        ImageView ivB = (ImageView) findViewById(R.id.iv_b);
        ShapeDrawable sdB = new ShapeDrawable(new OvalShape());
        sdB.setIntrinsicHeight(100);
        sdB.setIntrinsicWidth(100);
        sdB.getPaint().setColor(Color.parseColor("#0000ff"));
        ivB.setImageDrawable(sdB);
    }
}
