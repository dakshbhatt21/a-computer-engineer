package com.acomputerengineer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CanvasDemoActivity extends AppCompatActivity {

    Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button btnAddText = findViewById(R.id.btn_add_text);
        Button btnSaveImage = findViewById(R.id.btn_save_image);
        final ImageView iv = findViewById(R.id.iv);

        btnAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CanvasDemoActivity.this);
                final EditText edittext = new EditText(CanvasDemoActivity.this);

                alert.setTitle(getResources().getString(R.string.act_canvas_demo));
                alert.setMessage(getResources().getString(R.string.str_add_text));

                alert.setView(edittext);

                alert.setPositiveButton(getResources().getString(R.string.add), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String str = edittext.getText().toString();
                        View v = new CanvasWithText(getApplicationContext(), str);
                        Bitmap bitmap = Bitmap.createBitmap(500/*width*/, 500/*height*/, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        v.draw(canvas);
                        b = bitmap;     //for saving "b" to the sdcard
                        iv.setImageBitmap(bitmap);
                    }
                });

                alert.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

        btnSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create directory if not exist
                final File dir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File output = new File(dir, "canvasdemo.jpg");
                OutputStream os = null;

                try {
                    os = new FileOutputStream(output);
                    b.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.flush();
                    os.close();

                    final Handler handler = new Handler();

                    //this code will scan the image so that it will appear in your gallery when you open next time
                    MediaScannerConnection.scanFile(CanvasDemoActivity.this, new String[]{output.toString()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(CanvasDemoActivity.this, CanvasDemoActivity.this.getResources().getString(R.string.str_save_image_text) + dir.getPath(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }
                    );
                } catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });

    }

    //your canvas as per your requirement
    public class CanvasWithText extends View {

        String str;

        public CanvasWithText(Context context, String str) {
            super(context);
            this.str = str;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint pBackground = new Paint();
            pBackground.setColor(Color.WHITE);
            canvas.drawRect(0, 0, 512, 512, pBackground);
            Paint pText = new Paint();
            pText.setColor(Color.BLACK);
            pText.setTextSize(20);
            canvas.drawText(str, 100, 100, pText);
        }
    }
}
