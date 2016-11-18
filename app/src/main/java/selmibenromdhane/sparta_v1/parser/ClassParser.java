package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.activity.DetailsClassesActivity;
import selmibenromdhane.sparta_v1.adapter.CustomAdapter;
import selmibenromdhane.sparta_v1.manager.Course;

/**
 * Created by Oclemy on 6/5/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class ClassParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    ListView lv;
    GridView lvv;
    public static String DESCRIPTION_EXTRA="course_desc";
    public static String COURSE_EXTRA="course_code";
    public static String IMAGE_EXTRA="course_cover";



    ProgressDialog pd;
    ArrayList<Course> spacecrafts=new ArrayList<>();

    public ClassParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    public ClassParser(Context c, String jsonData, GridView lvv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lvv = lvv;
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
            final CustomAdapter adapter=new CustomAdapter(c,spacecrafts);
//            lv.setAdapter(adapter);
            lvv.setAdapter(adapter);
            lvv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("ssssssssssss");
                    Toast.makeText(c,"SSSS",Toast.LENGTH_LONG).show();

                    Course item = (Course) adapter.getItem(position);


                    Intent intent=new Intent(c, DetailsClassesActivity.class);
                    // intent.putExtra("item",parent.);



                    intent.putExtra(DESCRIPTION_EXTRA, item.getCourse_desc());
                    intent.putExtra(COURSE_EXTRA, item.getCourse_code());
                    intent.putExtra(IMAGE_EXTRA,item.getCourse_cover());
                    //intent.putExtra(SCHEDULE_HOUR, item.getHour());

                    c.startActivity(intent);

                }
            });
        }
    }

    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            spacecrafts.clear();
            Course course;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                int id=jo.getInt("0");
                String desc=jo.getString("1");
                String name=jo.getString("2");
              String imageUrl=jo.getString("3");
             //   System.out.println(imageUrl);
                imageUrl="https://spartaapp.azurewebsites.net/Backend/partials/user_images/"+imageUrl;

                System.out.println(imageUrl);

                course=new Course();

                course.setCourse_crn(id);
                course.setCourse_code(name);
                course.setCourse_cover(imageUrl);
                course.setCourse_desc(desc);
                spacecrafts.add(course);
            }

            return 1;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}









