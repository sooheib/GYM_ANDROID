package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import selmibenromdhane.sparta_v1.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash_screen);



        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreenActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }


    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }
}
