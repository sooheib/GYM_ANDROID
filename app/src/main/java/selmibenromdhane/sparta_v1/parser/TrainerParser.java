package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.yalantis.flipviewpager.utils.FlipSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.adapter.TrainersAdapter;
import selmibenromdhane.sparta_v1.manager.Trainer;

/**
 * Created by sooheib on 11/29/16.
 */

public class TrainerParser  extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    ListView lv;
    ProgressDialog pd;
    ArrayList<Trainer> trainers=new ArrayList<>();



    public TrainerParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
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
            FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
            final TrainersAdapter adapter=new TrainersAdapter(c,trainers,settings);
            lv.setAdapter(adapter);

//            //   lvv.setAdapter(adapter);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    System.out.println("ssssssssssss");
//                    Toast.makeText(c,"SSSS",Toast.LENGTH_LONG).show();
//
//                    Trainer item = (Trainer) adapter.getItem(position);
//
//
//                    Intent intent=new Intent(c, DetailScheduleActivity.class);
//                    // intent.putExtra("item",parent.);
//
//
////                    intent.putExtra(SCHEDULE_ID, item.getSchedule_i());
////                    intent.putExtra(SCHEDULE_DATE, item.getDay());
////                    intent.putExtra(COURSE_EXTRA, item.getCourse());
////                    intent.putExtra(TRAINER_EXTRA, item.getTrainer());
////                    intent.putExtra(SCHEDULE_HOUR, item.getStartTime());
////                    intent.putExtra(COURSE_CAPACITY, item.getCourse_maxC());//int
////                    intent.putExtra(COURSE_COVER, item.getCourse_cover());
////                    intent.putExtra(COURSE_DESC, item.getCourse_desc());
////                    intent.putExtra(TRAINER_PHOTO, item.getTrainer_photo());
////                    intent.putExtra(ROOM_NUMBER, item.getRoom_name());
////                    intent.putExtra(COUNTMEMBER,item.getCountMumber());
//
//                    c.startActivity(intent);
//
//                }
//            });
        }
    }


    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            trainers.clear();

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Trainer trainer=new Trainer();
                trainer.setFirst_name(jo.getString("1"));
                System.out.println(jo.getString("1"));
                trainer.setLast_name(jo.getString("2"));
                System.out.println(jo.getString("2"));
                trainer.setPhoto("https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/"+jo.getString("6"));
                System.out.println("https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/"+jo.getString("6"));

                // adding Billionaire to worldsBillionaires array
                trainers.add(trainer);
            }

            return 1;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
