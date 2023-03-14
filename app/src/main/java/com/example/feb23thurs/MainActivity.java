package com.example.feb23thurs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button zero;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;

    int time;
    int time2;
    int time3;

    int speed;
    int speed2;
    int speed3;

    CountEvent event;
    CountEvent event2;
    CountEvent event3;
    Handler handler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        zero = findViewById(R.id.zero);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        Toast t = Toast.makeText(getApplicationContext(), "CRIPPLING. ADDICTION.", Toast.LENGTH_SHORT);
        time = 0;
        time2 = 0;
        time3 = 0;
        Random random = new Random();
        speed = (200+random.nextInt(1200));
        speed2 = (200+random.nextInt(1200));;
        speed3 = (200+random.nextInt(1200));;

        event = new CountEvent(time, imageView, speed);
        event2 = new CountEvent(time2, imageView2, speed2);
        event3 = new CountEvent(time3, imageView3, speed3);
        handler = new Handler();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start.getText().equals("Start")){
                    handler.postDelayed(event, speed);
                    handler.postDelayed(event2, speed2);
                    handler.postDelayed(event3, speed3);
                    start.setText("Stop");
                }  else if (start.getText().equals("Stop") && (imageView.getDrawable().getConstantState() == imageView2.getDrawable().getConstantState() && imageView.getDrawable().getConstantState() == imageView3.getDrawable().getConstantState())){
                        // If statement is a little over the top, I'm not sure why but it wasn't working when I tried to use time, time2 and time3.
                        // I also tried event.time, event2.time and event3.time but those didn't seem to work either.
                        handler.removeCallbacks(event);
                        handler.removeCallbacks(event2);
                        handler.removeCallbacks(event3);
                        t.show();
                        start.setText("Start");
                }
                else {
                    handler.removeCallbacks(event);
                    handler.removeCallbacks(event2);
                    handler.removeCallbacks(event3);
                    start.setText("Start");
                }

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (start.getText().equals("Stop")){

                } else {
                    time = 0;
                    time2 = 0;
                    time3 = 0;
                    imageView.setImageResource(R.drawable.grape);
                    imageView2.setImageResource(R.drawable.grape);
                    imageView3.setImageResource(R.drawable.grape);
                }
            }
        });
    }

    private class CountEvent implements Runnable {
        int time;
        ImageView here;
        int speed;

        public CountEvent(int time, ImageView here, int speed){
            this.time = time;
            this.here = here;
            this.speed = speed;

        }

        @Override
        public void run(){
            if (start.getText().equals("Stop")){
                time++;
                switch (time){
                    case 1: here.setImageResource(R.drawable.grape);
                        break;
                    case 2: here.setImageResource(R.drawable.strawberry);
                        break;
                    case 3: here.setImageResource(R.drawable.pear);
                        break;
                }
                time = time%4;
            } else {

            }
        handler.postDelayed(this, speed);
        }
    }
}