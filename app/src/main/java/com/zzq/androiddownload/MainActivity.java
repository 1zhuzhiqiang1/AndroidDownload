package com.zzq.androiddownload;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btn_down = null;

    final String url = "http://192.168.40.203:8080/cordova/www/chcp.json";
    final String path = Environment.getExternalStorageDirectory() + File.separator + "chcp.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_down = (Button) findViewById(R.id.btn_down);

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            DownloadUtils.download(url, path);
                        } catch (IOException e) {
                            Log.e("TAG", e.toString());
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "下载出错", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        super.onPostExecute(aVoid);
                    }
                }.execute();
            }
        });
    }
}
