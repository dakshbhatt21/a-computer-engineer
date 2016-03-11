package com.acomputerengineer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PickImageActivity extends AppCompatActivity {

    ImageView iv = null;
    TextView tvImagePath = null;
    String strImagePath = "no image selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Button btnPickImage = (Button) findViewById(R.id.btn_pick_image);
        iv = (ImageView) findViewById(R.id.iv);
        tvImagePath = (TextView) findViewById(R.id.tv_image_path);

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.str_select_image)), 1);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.str_select_image)), 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && data.getData() != null && resultCode == RESULT_OK) {
            ParcelFileDescriptor pfd;
            try {
                pfd = getContentResolver().openFileDescriptor(data.getData(), "r");
                FileDescriptor fd = pfd.getFileDescriptor();
                Bitmap img = BitmapFactory.decodeFileDescriptor(fd);
                pfd.close();
                iv.setImageBitmap(img); //image represent ImageVIew to display picked image
                if ( android.os.Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT){
                    int flags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    Uri u = data.getData();
                    getContentResolver().takePersistableUriPermission(u, flags);
                    String id = u.getLastPathSegment().split(":")[1];
                    final String[] imageColumns = {MediaStore.Images.Media.DATA};
                    final String imageOrderBy = null;
                    Uri u1 = null;
                    String state = Environment.getExternalStorageState();
                    if (!state.equalsIgnoreCase(Environment.MEDIA_MOUNTED))
                        u1 = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
                    else u1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    Cursor c = managedQuery(u1, imageColumns, MediaStore.Images.Media._ID + "=" + id, null, imageOrderBy);
                    if (c.moveToFirst()) {
                        strImagePath = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA)); //imgPath represents string variable to hold the path of image
                    }
                }else{
                    Uri imgUri = data.getData();
                    Cursor c1 = getContentResolver().query(imgUri, null, null, null, null);
                    if (c1 == null) {
                        strImagePath = imgUri.getPath(); //imgPath represents string variable to hold the path of image
                    } else {
                        c1.moveToFirst();
                        int idx = c1.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        strImagePath = c1.getString(idx); //imgPath represents string variable to hold the path of image
                        c1.close();
                    }
                }
                tvImagePath.setText(getResources().getString(R.string.str_image_path) + ": " + strImagePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } super.onActivityResult(requestCode, resultCode, data);
    }
}
