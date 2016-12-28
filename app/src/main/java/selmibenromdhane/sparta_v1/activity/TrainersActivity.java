package selmibenromdhane.sparta_v1.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;

import selmibenromdhane.sparta_v1.parser.TrainerDownloader;


public class TrainersActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainers);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        final ListView friends = (ListView) findViewById(R.id.friends);

        new TrainerDownloader(TrainersActivity.this, AppConfig.URL_TRAINER,friends).execute();


    }
}

