package com.acomputerengineer;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(llm);

        ArrayList<String> alPost = new ArrayList<>();
        alPost.add("SHAREDPREFERENCES IN ANDROID KOTLIN (WITH HOW TO REMEMBER USER LOGIN/SESSION EXAMPLE)");
        alPost.add("DIFFERENT COLORS FOR SELECTED TAB IN BOTTOMNAVIGATIONVIEW IN ANDROID KOTLIN(PROGRAMMATICALLY)");
        alPost.add("POPULATE AND MANIPULATE AUTOCOMPLETETEXTVIEW IN ANDROID");
        alPost.add("OPEN CHAT PAGE IN WHATSAPP FOR GIVEN NUMBER IN ANDROID");
        alPost.add("DISPLAY IMAGE GRID IN RECYCLERVIEW IN KOTLIN ANDROID");
        alPost.add("ROOM SQLITE DEMO WITH CRUD OPERATIONS IN ANDROID");
        alPost.add("DOWNLOAD IMAGE AND SAVE IT TO SDCARD(PHONE STORAGE) WITHOUT ANY LIBRARY IN ANDROID");
        alPost.add("CREATE PDF FILE AND SAVE IT TO SDCARD IN ANDROID");
        alPost.add("DRAW LINE USING FINGER ON CANVAS IN ANDROID");
        alPost.add("DISPLAY LIST IN ALERTDIALOG IN ANDROID(SIMPLE LIST, RADIO BUTTON LIST, CHECK BOX LIST)");
        alPost.add("DISPLAY IMAGE GRID IN RECYCLERVIEW IN ANDROID");
        alPost.add("PICK IMAGE FROM GALLERY BEFORE AND AFTER KITKAT VERSION IN ANDROID(UPDATED)");
        alPost.add("HOW TO DISPLAY CANVAS ON IMAGEVIEW AND SAVE CANVAS AS BITMAP AND STORE IN SDCARD IN ANDROID");
        alPost.add("DRAW CIRCLE SHAPE IN IMAGEVIEW IN ANDROID");
        alPost.add("LIMIT NUMBER RANGE IN EDITTEXT USING INPUTFILTER IN ANDROID");
        alPost.add("RESIZE IMAGE DURING DECODE FROM FILE TO BITMAP IN ANDROID(TO PREVENT OOM)");
        alPost.add("VARIOUS MATERIAL DESIGNS FOR BUTTON IN ANDROID");
        alPost.add("SHARE IMAGE TO WHATSAPP IN ANDROID");
        alPost.add("CRUD FUNCTIONS IN SQLITE IN ANDROID");

        PostAdapter adapter = new PostAdapter(alPost);
        rv.setAdapter(adapter);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

        ArrayList<String> alPost;

        PostAdapter(ArrayList<String> alPost) {
            this.alPost = alPost;
        }

        public class PostViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView tvName;

            public PostViewHolder(View itemView) {
                super(itemView);
                cv = itemView.findViewById(R.id.cv);
                tvName = itemView.findViewById(R.id.tv_name);

                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        switch (position) {
                            case 0:
                                Intent intentSharedPreferences = new Intent(MainActivity.this, SharedPreferencesActivity.class);
                                startActivity(intentSharedPreferences);
                                break;
                            case 1:
                                Intent intentBottomNavigationView = new Intent(MainActivity.this, BottomNavigationViewActivity.class);
                                startActivity(intentBottomNavigationView);
                                break;
                            case 2:
                                Intent intentAutoCompleteTextView = new Intent(MainActivity.this, AutoCompleteTextViewActivity.class);
                                startActivity(intentAutoCompleteTextView);
                                break;
                            case 3:
                                Intent intentOpenWhatsappNumber = new Intent(MainActivity.this, OpenWhatsappNumberActivity.class);
                                startActivity(intentOpenWhatsappNumber);
                                break;
                            case 4:
                                Intent intentImageGridKotlin = new Intent(MainActivity.this, ImageGridKotlinActivity.class);
                                startActivity(intentImageGridKotlin);
                                break;
                            case 5:
                                Intent intentRoomSQLite = new Intent(MainActivity.this, RoomSQLiteActivity.class);
                                startActivity(intentRoomSQLite);
                                break;
                            case 6:
                                Intent intentDownloadImage = new Intent(MainActivity.this, DownloadImageActivity.class);
                                startActivity(intentDownloadImage);
                                break;
                            case 7:
                                Intent intentPDF = new Intent(MainActivity.this, PDFActivity.class);
                                startActivity(intentPDF);
                                break;
                            case 8:
                                Intent intentDrawLineWithFinger = new Intent(MainActivity.this, DrawLineWithFingerActivity.class);
                                startActivity(intentDrawLineWithFinger);
                                break;
                            case 9:
                                Intent intentListAlertDialog = new Intent(MainActivity.this, ListAlertDialogActivity.class);
                                startActivity(intentListAlertDialog);
                                break;
                            case 10:
                                Intent intentImageGrid = new Intent(MainActivity.this, ImageGridActivity.class);
                                startActivity(intentImageGrid);
                                break;
                            case 11:
                                Intent intentPickImage = new Intent(MainActivity.this, PickImageActivity.class);
                                startActivity(intentPickImage);
                                break;
                            case 12:
                                Intent intentCanvasDemo = new Intent(MainActivity.this, CanvasDemoActivity.class);
                                startActivity(intentCanvasDemo);
                                break;
                            case 13:
                                Intent intentCircleImageView = new Intent(MainActivity.this, CircleImageViewActivity.class);
                                startActivity(intentCircleImageView);
                                break;
                            case 14:
                                Intent intentLimitNumberRange = new Intent(MainActivity.this, LimitNumberRangeActivity.class);
                                startActivity(intentLimitNumberRange);
                                break;
                            case 15:
                                Intent intentResizeImageDecodeBitmap = new Intent(MainActivity.this, ResizeImageDecodeBitmapActivity.class);
                                startActivity(intentResizeImageDecodeBitmap);
                                break;
                            case 16:
                                Intent intentMaterialDesignButtons = new Intent(MainActivity.this, MaterialDesignButtonsActivity.class);
                                startActivity(intentMaterialDesignButtons);
                                break;
                            case 17:
                                Intent intentShareImageWhatsapp = new Intent(MainActivity.this, ShareImageWhatsappActivity.class);
                                startActivity(intentShareImageWhatsapp);
                                break;
                            case 18:
                                Intent intentSqliteCRUD = new Intent(MainActivity.this, SqliteCRUDActivity.class);
                                startActivity(intentSqliteCRUD);
                                break;
                        }

                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return alPost.size();
        }

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
            PostViewHolder pvh = new PostViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(final PostViewHolder personViewHolder, int i) {
            personViewHolder.tvName.setText(String.valueOf(alPost.get(i)));
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}
