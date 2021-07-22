package com.example.foundnlost;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private int Sleep_Timer = 3;

    private static final String TAG = "slashscreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private void setupUI()
    {

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000 * Sleep_Timer);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreen.this,login.class);
            startActivity(intent);
            SplashScreen.this.finish();
        }
    }

}