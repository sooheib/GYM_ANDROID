package selmibenromdhane.sparta_v1.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.parser.ClassDownloader;

public class Main2Activity extends AppCompatActivity {
    final static String urlAddress="http://192.168.1.3/android2016/getClass.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final GridView lv= (GridView) findViewById(R.id.lv);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new ClassDownloader(Main3Activity.this,urlAddress,lv).execute();
                new ClassDownloader(Main2Activity.this,urlAddress,lv).execute();
            }
        });
    }


}
