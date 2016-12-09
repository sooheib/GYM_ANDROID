package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailsClassesActivity;
import selmibenromdhane.sparta_v1.activity.DetailsClassesActivity1;
import selmibenromdhane.sparta_v1.manager.Course;
import selmibenromdhane.sparta_v1.utils.PicassoClient;

public class ClassesRecyclerViewAdapter extends RecyclerView.Adapter<ClassesRecyclerViewHolders> {

    private ArrayList<Course> itemList;
    private Context context;
    public static String DESCRIPTION_EXTRA="course_desc";
    public static String COURSE_EXTRA="course_code";
    public static String IMAGE_EXTRA="course_cover";
    ImageView img;

    public ClassesRecyclerViewAdapter(Context context, ArrayList<Course> itemList) {
        this.itemList = itemList;
        this.context = context;

    }

    @Override
    public ClassesRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_item_layout, null);
        ClassesRecyclerViewHolders rcv = new ClassesRecyclerViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(ClassesRecyclerViewHolders holder, int position) {
        final Course s=itemList.get(position);

        holder.className.setText(itemList.get(position).getCourse_code());
        PicassoClient.downloadImage(context, itemList.get(position).getCourse_cover(),holder.classPhoto);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                openDetailActivity(s.getCourse_code(),s.getCourse_desc(),s.getCourse_cover());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    private void openDetailActivity(String name,String desc,String imageUrl)
    {
        Intent i=new Intent(context, DetailsClassesActivity1.class);
        i.putExtra(COURSE_EXTRA,name);
        i.putExtra(DESCRIPTION_EXTRA,desc);
        i.putExtra(IMAGE_EXTRA,imageUrl);

        context.startActivity(i);
    }
}