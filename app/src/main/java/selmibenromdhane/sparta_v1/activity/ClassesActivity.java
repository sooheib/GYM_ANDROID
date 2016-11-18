package selmibenromdhane.sparta_v1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.parser.ClassDownloader;

public class ClassesActivity extends BaseActivity {
    private GridView gridView;

    //final static String urlAddress="https://spartax.000webhostapp.com/Upload/getClass.php";

  //  final static String urlAddress="https://spartax.000webhostapp.com/Backend/android_api/getClass.php";
    //public static String URL_LOGIN = "https://spartax.000webhostapp.com/Backend/android_api/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);


//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        gridView = (GridView) findViewById(R.id.gridView);
//        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
//        gridView.setAdapter(gridAdapter);


        new ClassDownloader(ClassesActivity.this, AppConfig.URL_CLASSES,gridView).execute();


        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
//
//                //Create intent
//                Intent intent = new Intent(ClassesActivity.this, DetailsActivity.class);
//                intent.putExtra("title", item.getTitle());
////              intent.putExtra("image", item.getImage());
////                System.out.println(item.getTitle());
//                //Start details activity
//                startActivity(intent);
            }
        });
    }

    /**
     * Prepare some dummy data for gridview
     */
//    private ArrayList<ImageItem> getData() {
//        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
//            String url = AppConfig.URL_CLASSES+i;
//
//
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, 0));
//            imageItems.add(new ImageItem(bitmap, "Image#" + i));
//        }
//        return imageItems;
//    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle arrow click here
//    if (item.getItemId() == android.R.id.home) {
////            finish(); // close this activity and return to preview activity (if there is any)
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
//        return super.onOptionsItemSelected(item);
//
//    }
//    @Override
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
//    }




}

