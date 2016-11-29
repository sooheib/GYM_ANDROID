package selmibenromdhane.sparta_v1.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.parser.ClassesCardDownloader;

public class ClassesActivity extends BaseActivity{
    private LinearLayoutManager lLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        lLayout = new LinearLayoutManager(ClassesActivity.this);


        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        new ClassesCardDownloader(ClassesActivity.this, AppConfig.URL_CLASSES,rView).execute();

    }
}
