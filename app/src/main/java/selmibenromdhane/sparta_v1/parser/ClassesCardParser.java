package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.adapter.ClassesRecyclerViewAdapter;
import selmibenromdhane.sparta_v1.manager.Course;

/**
 * Created by sooheib on 11/25/16.
 */

public class ClassesCardParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    RecyclerView rv;
    public static String DESCRIPTION_EXTRA="course_desc";
    public static String COURSE_EXTRA="course_code";
    public static String IMAGE_EXTRA="course_cover";



    ProgressDialog pd;
    ArrayList<Course> spacecrafts=new ArrayList<>();



    public ClassesCardParser(Context c, String jsonData, RecyclerView rv) {
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
            final ClassesRecyclerViewAdapter recyclerViewAdapter=new ClassesRecyclerViewAdapter(c,spacecrafts);
           // final CustomAdapter adapter=new CustomAdapter(c,spacecrafts);
//            lv.setAdapter(adapter);
            rv.setAdapter(recyclerViewAdapter);
//            rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    System.out.println("ssssssssssss");
//                    Toast.makeText(c,"SSSS",Toast.LENGTH_LONG).show();
//
//                    Course item = (Course) adapter.getItem(position);
//
//
//                    Intent intent=new Intent(c, DetailsClassesActivity.class);
//                    // intent.putExtra("item",parent.);
//
//
//
//                    intent.putExtra(DESCRIPTION_EXTRA, item.getCourse_desc());
//                    intent.putExtra(COURSE_EXTRA, item.getCourse_code());
//                    intent.putExtra(IMAGE_EXTRA,item.getCourse_cover());
//                    //intent.putExtra(SCHEDULE_HOUR, item.getHour());
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
