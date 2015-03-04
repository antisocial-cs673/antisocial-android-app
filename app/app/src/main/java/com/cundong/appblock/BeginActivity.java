package com.cundong.appblock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
/**
 * Created by countdyang on 2015/3/2.
 */
public class BeginActivity extends Activity {
    private ImageView welcomeImg = null;
    private ImageView facebookView1 = null;
    private ImageView whatsappView1 = null;
    private ImageView viberView1 = null;
    private ImageView twitterView1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        welcomeImg = (ImageView) this.findViewById(R.id.begin);

        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(3000);// set the time
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }

    private class AnimationImpl implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.antisocial);

            facebookView1 = (ImageView) findViewById(R.id.facebookView);
            whatsappView1 = (ImageView) findViewById(R.id.whatsappView);
            viberView1 = (ImageView) findViewById(R.id.viberView);
            twitterView1 = (ImageView) findViewById(R.id.twitterView);


            AlphaAnimation facebook = new AlphaAnimation(0.0f, 1.0f);
            facebook.setDuration(300);
            facebookView1.startAnimation(facebook);
            facebook.setRepeatCount(-1);


            AlphaAnimation whatsapp = new AlphaAnimation(0.0f, 1.0f);
            whatsapp.setDuration(300);
            whatsappView1.startAnimation(whatsapp);
            whatsapp.setRepeatCount(-1);


            AlphaAnimation viber = new AlphaAnimation(0.0f, 1.0f);
            viber.setDuration(300);
            viberView1.startAnimation(viber);
            viber.setRepeatCount(-1);


            AlphaAnimation twitter = new AlphaAnimation(0.0f, 1.0f);
            twitter.setDuration(300);
            twitterView1.startAnimation(twitter);
            twitter.setRepeatCount(-1);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // after finish jump to other part
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void skip() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}