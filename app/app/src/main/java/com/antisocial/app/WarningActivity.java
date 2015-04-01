package com.antisocial.app;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class WarningActivity extends Activity {
    MediaPlayer dMediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_warning);
        dMediaPlayer= MediaPlayer.create(this, R.raw.warningmusic);
        dMediaPlayer.setVolume(1,1);
        dMediaPlayer.start();
	}

	@Override
	public void onBackPressed() {
        //Delay is used to make it more
        new Handler().postDelayed(new Runnable(){
            public void run() {
                Intent MyIntent = new Intent(Intent.ACTION_MAIN);
                MyIntent.addCategory(Intent.CATEGORY_HOME);
                startActivity(MyIntent);

                finish();

                return;//execute the task
            }
        }, 3000);
	}
}