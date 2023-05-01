package com.aetherbreaker.cis225a8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private ImageView diceView;
    private TextView textResults;
    private Bitmap[] scaledDiceResults;
    private Bitmap[] scaledDiceAnimationSteps;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        diceView = findViewById(R.id.diceView);
        textResults = findViewById(R.id.textResults);

        Bitmap[] diceResults = new Bitmap[]{
            BitmapFactory.decodeResource(getResources(), R.drawable.one),
            BitmapFactory.decodeResource(getResources(), R.drawable.two),
            BitmapFactory.decodeResource(getResources(), R.drawable.three),
            BitmapFactory.decodeResource(getResources(), R.drawable.four),
            BitmapFactory.decodeResource(getResources(), R.drawable.five),
            BitmapFactory.decodeResource(getResources(), R.drawable.six)
        };


        Bitmap[] diceAnimationSteps = new Bitmap[]{BitmapFactory.decodeResource(getResources(), R.drawable.step0),
            BitmapFactory.decodeResource(getResources(), R.drawable.step1),
            BitmapFactory.decodeResource(getResources(), R.drawable.step2),
            BitmapFactory.decodeResource(getResources(), R.drawable.step3),
            BitmapFactory.decodeResource(getResources(), R.drawable.step4),
            BitmapFactory.decodeResource(getResources(), R.drawable.step5),
            BitmapFactory.decodeResource(getResources(), R.drawable.step6),
            BitmapFactory.decodeResource(getResources(), R.drawable.step7),
            BitmapFactory.decodeResource(getResources(), R.drawable.step8)
        };

        diceView.post(new Runnable() {
            @Override
            public void run() {
                scaledDiceResults = new Bitmap[diceResults.length];
                scaledDiceAnimationSteps = new Bitmap[diceAnimationSteps.length];

                for(int i = 0; i < diceResults.length; i++){
                    scaledDiceResults[i] = Bitmap.createScaledBitmap(
                            diceResults[i],
                            diceView.getWidth(),
                            diceView.getHeight(),
                            false
                    );
                }

                for(int i = 0; i < diceAnimationSteps.length; i++){
                    scaledDiceAnimationSteps[i] = Bitmap.createScaledBitmap(
                            diceAnimationSteps[i],
                            diceView.getWidth(),
                            diceView.getHeight(),
                            false
                    );
                }

                diceView.setBackground(new BitmapDrawable(getResources(), scaledDiceResults[5]));


            }
        });



        // on click listener for diceView
        mainLayout.setOnClickListener(v -> {
                AnimationDrawable diceAnimation = new AnimationDrawable();

                for (Bitmap scaledDiceAnimationStep : scaledDiceAnimationSteps) {
                    diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceAnimationStep), 85);
                }

                // random number between 1 and 6
                int random = (int) (Math.random() * 6 + 1);

                switch (random) {
                    case 1:
                        diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceResults[0]), 85);
                        break;
                    case 2:
                        diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceResults[1]), 85);
                        break;
                    case 3:
                        diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceResults[2]), 85);
                        break;
                    case 4:
                        diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceResults[3]), 85);
                        break;
                    case 5:
                        diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceResults[4]), 85);
                        break;
                    case 6:
                        diceAnimation.addFrame(new BitmapDrawable(getResources(), scaledDiceResults[5]), 85);
                        break;
                }
                diceAnimation.setOneShot(true);
                diceView.setBackground(diceAnimation);
                diceAnimation.start();

                textResults.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                textResults.setText(String.valueOf(random));
                            }
                        },
                        850
                );
            }
        );
    }
}