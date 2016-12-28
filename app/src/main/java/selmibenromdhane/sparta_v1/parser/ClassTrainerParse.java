package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.activity.DetailsClassesActivity1;
import selmibenromdhane.sparta_v1.adapter.ClassesRecyclerViewAdapter;
import selmibenromdhane.sparta_v1.adapter.HorizontalAdaptar;
import selmibenromdhane.sparta_v1.manager.Course;
import selmibenromdhane.sparta_v1.manager.Trainer;

/**
 * Created by sooheib on 12/27/16.
 */

public class ClassTrainerParse  extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    RecyclerView rv;

    public static int INVALID_POSITION = -1;

    DetailsClassesActivity1 detailsClassesActivity1=new DetailsClassesActivity1();
    String class_id;
    ProgressDialog pd;
    ArrayList<Trainer> trainers=new ArrayList<>();

    public ClassTrainerParse(Context c, String jsonData, RecyclerView rv) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }
    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }else {
            //BIND DATA TO LISTVIEW
            System.out.println("horrrrrrrrrr");

            final HorizontalAdaptar cr=new HorizontalAdaptar(c,trainers);

            initRecyclerView(rv, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), cr);

        }
    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final HorizontalAdaptar adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (INVALID_POSITION != adapterPosition) {
                   // final int value = adapter.mP[adapterPosition];
                    final int value=adapter.mPosition[adapterPosition];
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
                }
            }
        });
    }


    private int parseData() {
        class_id = detailsClassesActivity1.course_id;
        System.out.println("ccccc" + class_id);
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;

            trainers.clear();
            Trainer trainer;

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);

                String id = jo.getString("14");
                System.out.println("iddddddd"+id+" "+class_id);

                if (id.equalsIgnoreCase(class_id)) {

                    //intent.putExtra(TRAINER_EXTRA, item.getTrainer());
                    trainer = new Trainer();
                    String trainername = jo.getString("7");
                    trainer.setLast_name(trainername);
                    System.out.println(trainername);

                    // intent.putExtra(TRAINER_PHOTO, item.getTrainer_photo());
                    String trainerphoto = jo.getString("8");
                    trainerphoto = "https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/" + trainerphoto;

                    trainer.setPhoto(trainerphoto);
                    System.out.println(trainerphoto);

                    trainers.add(trainer);
                }

            }
                return 1;


            }catch(JSONException e){
                e.printStackTrace();
            }

            return 0;

        }

}
