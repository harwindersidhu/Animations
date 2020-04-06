/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.propertyanimation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Demonstrates property animation with ObjectAnimator and AnimatorSet.
 * Shows an expanding and contracting circle.
 */

public class MainActivity extends AppCompatActivity {
    private TextView youDidIt;
    ImageView hatImage;
    private Button throwHatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youDidIt = findViewById(R.id.congrats);
        hatImage = findViewById(R.id.hat_image);
        throwHatButton = findViewById(R.id.throw_button);
    }

    public void congratsPressed(View view) {
        ScaleAnimation growAnimation = new ScaleAnimation(1f, 2.0f, 1f, 2.0f, youDidIt.getWidth() / 2.0f, youDidIt.getHeight() / 2.0f);
        growAnimation.setDuration(2000);
        //growAnimation.setRepeatCount(3);
        final ScaleAnimation shrinkAnimation = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, youDidIt.getWidth() / 2.0f, youDidIt.getHeight() / 2.0f);
        shrinkAnimation.setDuration(2000);
        growAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Scale Animation", "Grow Animation Started");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Scale Animation", "Grow Animation Ended");
                youDidIt.setAnimation(shrinkAnimation);
                shrinkAnimation.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(growAnimation);
        //animationSet.setRepeatCount(3);
        youDidIt.startAnimation(animationSet);
    }

    public void throwHatPressed(View view) {
        Animation animUpDown;

        // load the animation
        animUpDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_up_down);

        animUpDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Scale Animation", "Move Animation Started");
                throwHatButton.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Scale Animation", "Move Animation Ended");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        float mAngleToRotate = 360f;
        RotateAnimation hatRotation = new RotateAnimation(0.0f, mAngleToRotate, hatImage.getWidth()/2.0f, hatImage.getHeight()/2.0f);
        hatRotation.setDuration(3000);
        hatRotation.setInterpolator(this, android.R.interpolator.linear);
        hatRotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Scale Animation", "Rotation Animation Ended");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Scale Animation", "Rotation Animation Ended");
                throwHatButton.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(hatRotation);
        animationSet.addAnimation(animUpDown);
        hatImage.startAnimation(animationSet);

    }
}
