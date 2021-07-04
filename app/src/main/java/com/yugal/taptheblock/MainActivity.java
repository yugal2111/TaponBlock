package com.yugal.taptheblock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    LinearLayout blue, green, orange, yellow;
    TextView finalScore, textView, tryAgain;
    Handler handler;
    Timer timer;
    final static int INTERVAL = 1000;
    final static int S_INTERVAL = 3000;
    int score = 0;
    int i = 0;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewID(); //Find all ID's
        dialogBox(); // Gamer Over Dialog box


        orange.setOnClickListener(v -> {
            flag = 1;
            ColorDrawable viewColor = (ColorDrawable) orange.getBackground();
            int colorId = viewColor.getColor();
            if (colorId == getResources().getColor(R.color.gray)) {
                score = score + 1;
                textView.setText("Score : " + score);
            } else {
                finalScore.setText("Score : " + score);
                dialog.show();
            }
        });

        blue.setOnClickListener(v -> {
            flag = 1;
            ColorDrawable viewColor = (ColorDrawable) blue.getBackground();
            int colorId = viewColor.getColor();
            if (colorId == getResources().getColor(R.color.gray)) {
                score = score + 1;
                textView.setText("Score : " + score);
            } else {
                ;
                finalScore.setText("Score : " + score);
                dialog.show();
            }
        });

        yellow.setOnClickListener(v -> {
            flag = 1;
            ColorDrawable viewColor = (ColorDrawable) yellow.getBackground();
            int colorId = viewColor.getColor();
            if (colorId == getResources().getColor(R.color.gray)) {
                score = score + 1;
                textView.setText("Score : " + score);
            } else {
                finalScore.setText("Score : " + score);
                dialog.show();
            }
        });


        green.setOnClickListener(v -> {
            flag = 1;
            ColorDrawable viewColor = (ColorDrawable) green.getBackground();
            int colorId = viewColor.getColor();
            if (colorId == getResources().getColor(R.color.gray)) {
                score = score + 1;
//                Toast.makeText(this, "Your Score is " + score, Toast.LENGTH_SHORT).show();
                textView.setText("Score : " + score);
            } else {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                finalScore.setText("Score : " + score);
                dialog.show();
            }
        });

        handler = new Handler();
        runnable();


        timer = new Timer(); // changed

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (i <= 4) {
                    i++;
                    int rnd = (int) (Math.random() * 4);
                    randomGrayColor(rnd);
                    orange.setBackgroundColor(getResources().getColor(R.color.orange));
                    blue.setBackgroundColor(getResources().getColor(R.color.blue));
                    green.setBackgroundColor(getResources().getColor(R.color.green));
                    yellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                } else {
                    i = 0;
                }
            }
        };
        timer.scheduleAtFixedRate(tt, 0, 1000);
    }

    private void dialogBox() {
        tryAgain.setOnClickListener(v -> {
            Intent i = getBaseContext().getPackageManager().
                    getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });

        dialog.setContentView(R.layout.activity_game_over);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.game_over;
        finalScore = dialog.findViewById(R.id.finalScore);
        tryAgain = dialog.findViewById(R.id.tryAgain);
    }

    private void randomGrayColor(int rnd) {
        handler.postDelayed(new Runnable() {
            public void run() {
                switch (rnd) {
                    case 0:
                        orange.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;

                    case 1:
                        blue.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;

                    case 2:
                        green.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;

                    case 3:
                        yellow.setBackgroundColor(getResources().getColor(R.color.gray));
                        break;
                }
            }
        }, INTERVAL);
    }

    private void findViewID() {
        dialog = new Dialog(MainActivity.this);
        textView = findViewById(R.id.textView);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        orange = findViewById(R.id.orange);
        yellow = findViewById(R.id.yellow);
    }

    private void runnable() {
        flag = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (flag == 0) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                        finalScore.setText("Score : " + score);
                        dialog.show();
                    }
                }
            }, this, S_INTERVAL);
        }

    }
}

