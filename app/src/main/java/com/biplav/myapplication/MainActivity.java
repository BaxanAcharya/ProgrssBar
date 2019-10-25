package com.biplav.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar,progressBar_Cyclic;
    private int progressStatus=0;
    private TextView textView, textViewCyclic;
    private Handler handler=new Handler(); //a handler allows communicating back with UI thread
    // from other background thread
   private Handler handler1=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView);
        progressBar_Cyclic=findViewById(R.id.progressBar_Cyclic);
        textViewCyclic=findViewById(R.id.textViewCyclic);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus<100)
                {
                    progressStatus +=1;
                    //update the progressbar and display the current value in the text view
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+ "/" +progressBar.getMax());
                        }
                    });
                    try {
                        //steep for 200 miliseconds
                        Thread.sleep(200);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus<100)
                {
                    progressStatus +=1;

                    handler1.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar_Cyclic.setProgress(progressStatus);
                            textViewCyclic.setText(progressStatus+ "/" +progressBar.getMax());
                        }
                    });
                    try {
                        //steep for 200 miliseconds
                        Thread.sleep(200);
                    }catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
