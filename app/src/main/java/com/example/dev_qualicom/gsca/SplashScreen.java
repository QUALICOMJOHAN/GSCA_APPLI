package com.example.dev_qualicom.gsca;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void StartAnimations() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        loadAnimation.reset();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin_lay);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(loadAnimation);

        loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        loadAnimation.reset();
        ImageView imageView = (ImageView) findViewById(R.id.splash);
        imageView.clearAnimation();
        imageView.startAnimation(loadAnimation);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 1000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            Club_Log.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    if (isNetworkAvailable()) {
                        startActivity(intent);
                    }

                    SplashScreen.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
