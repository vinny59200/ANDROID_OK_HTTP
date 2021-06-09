package com.example.questget2;

import android.os.AsyncTask;
import android.os.Bundle;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    TextView et;
    private static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (TextView) findViewById(R.id.AutoCompleteTextView01);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        new Task().execute();
    }

    @Override
    protected void onPause() {

        super.onPause();

        System.exit(0);

    }


    private class Task extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {

            System.out.println("vv in doinbackgrouund");
            progressBar.setProgress(20);
            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url("https://altequiz.osc-fr1.scalingo.io/question/1")
                    .build();
            System.out.println("vv in doinbackgrouund 0");
            progressBar.setProgress(40);
            String text = null;
            try (Response response = client.newCall(request).execute()) {
                System.out.println("vv in doinbackgrouund 1");
                progressBar.setProgress(60);
                text = response.body().string();
                System.out.println("vv in doinbackgrouund 2, text:"+text);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            progressBar.setProgress(100);

            et.setText(result);
        }
    }
}