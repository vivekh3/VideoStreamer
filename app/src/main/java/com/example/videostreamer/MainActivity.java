package com.example.videostreamer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView serverImage;
    Handler h = new Handler();
    private Socket s;
    //private PrintWriter pw;
    private boolean mconnectexception = false;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverImage = findViewById(R.id.video_frame);
        frame2video f2v = new frame2video();
        f2v.execute();
    }


    public class frame2video extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {


        }

        protected Void doInBackground(Void... params) {
            BufferedReader dis;
            String message;

            try {
                s = new Socket("192.168.0.132", 5001);
                //pw=new PrintWriter(s.getOutputStream());
                Log.d(TAG, "Connection Successful..!");
                //String msg="Connection Successful";
                //return msg;

            } catch (IOException e) {
                e.printStackTrace();
                mconnectexception = true;
            }


            if (mconnectexception) {
                Log.d(TAG, "Connection not Available");
            }


            while (true) {
                try {
                    if (s!=null) {
                        dis = new BufferedReader(new InputStreamReader(s.getInputStream(),"utf8"));
                        message = dis.readLine();
                        Log.d(TAG, "Here is it"+message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"Something is wrong");
                }

                return null;
            }
        }


    }
}


