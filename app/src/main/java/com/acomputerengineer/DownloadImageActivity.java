package com.acomputerengineer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private ImageView iv;

    private DownloadImage di;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        iv = findViewById(R.id.iv);

        pd = new ProgressDialog(DownloadImageActivity.this);
        pd.setMessage("Downloading image, please wait ...");
        pd.setIndeterminate(true);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.setProgressNumberFormat("%1d KB/%2d KB");

        di = new DownloadImage(DownloadImageActivity.this);
        di.execute("https://images.unsplash.com/photo-1536998533868-95cde0d71742?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=69a455127db97a5cc05e2d3c9c9ef245&auto=format&fit=crop&w=4000&q=80");

        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                di.cancel(true);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String, Integer, String> {

        private Context c;

        private DownloadImage(Context c) {
            this.c = c;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream is = null;
            OutputStream os = null;
            HttpURLConnection con = null;
            int length;
            try {
                URL url = new URL(sUrl[0]);
                con = (HttpURLConnection) url.openConnection();
                con.connect();

                if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "HTTP CODE: " + con.getResponseCode() + " " + con.getResponseMessage();
                }

                length = con.getContentLength();

                pd.setMax(length / (1000));

                is = con.getInputStream();
                os = new FileOutputStream(Environment.getExternalStorageDirectory() + File.separator + "a-computer-engineer.jpg");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = is.read(data)) != -1) {
                    if (isCancelled()) {
                        is.close();
                        return null;
                    }
                    total += count;
                    if (length > 0) {
                        publishProgress((int) total);
                    }
                    os.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (os != null)
                        os.close();
                    if (is != null)
                        is.close();
                } catch (IOException ignored) {
                }

                if (con != null)
                    con.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            pd.setIndeterminate(false);
            pd.setProgress(progress[0] / 1000);
        }

        @Override
        protected void onPostExecute(String result) {
            pd.dismiss();
            if (result != null) {
                Toast.makeText(c, "Download error: " + result, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(c, "Image downloaded successfully!", Toast.LENGTH_SHORT).show();

                Bitmap b = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "a-computer-engineer.jpg");
                iv.setImageBitmap(b);
            }
        }
    }
}