package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.TrainersActivity;
import selmibenromdhane.sparta_v1.manager.Trainer;

/**
 * Created by sooheib on 11/28/16.
 */

public class TrainersAdapter extends BaseFlipAdapter<Trainer> {

    private final int PAGES = 3;
    private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};
    Context context;
    ArrayList<Trainer> trainers;


    public TrainersAdapter(Context context, List<Trainer> items, FlipSettings settings) {
        super(context, items, settings);
        this.context=context;

    }

    @Override
    public View getPage(int position, View convertView, ViewGroup parent, Trainer item1, Trainer item2) {
        final TrainersHolder holder;
        Trainer trainer=getItem(position);


        if (convertView == null) {
            holder = new TrainersHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(TrainersActivity.LAYOUT_INFLATER_SERVICE);
               convertView =mInflater.inflate(R.layout.trainer_merge_page,null);
                       //inflater.inflate(R.layout.trainer_merge_page, parent, false);
            holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
            holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
            holder.infoPage = mInflater.inflate(R.layout.trainers_info, parent, false);
            holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);
            for (int id : IDS_INTEREST)
                holder.interests.add((TextView) holder.infoPage.findViewById(id));
            convertView.setTag(holder);
        }
        else {
            holder = (TrainersHolder) convertView.getTag();
        }
        switch (position) {
            // Merged page with 2 friends
            case 1:
                    //  Bitmap photoTrainer1 = urltoBitmap1.getBitmapFromURL(item1.getPhoto());

               Picasso.with(context).load(item1.getPhoto()).into(holder.leftAvatar);

              //  GridClient.loadPaintingImage(holder.leftAvatar, trainer);


                //       holder.rightAvatar.setImageDrawable(getResources().getDrawable(R.drawable.anastasia));

                 //   holder.leftAvatar.setImageBitmap(photoTrainer1);

                System.out.println("waw1");

                if (item2 != null) {
                    //  Bitmap photoTrainer2 = urltoBitmap2.getBitmapFromURL(item2.getPhoto());

                    // holder.leftAvatar.setImageDrawable(getResources().getDrawable(R.drawable.anastasia));

                    Picasso.with(context).load(item2.getPhoto()).into(holder.rightAvatar);

                    //GridClient.loadPaintingImage(holder.rightAvatar, trainer);


                    // holder.rightAvatar.setImageBitmap(photoTrainer2);

                    System.out.println("waw2");
                }
                break;
            default:
                fillHolder(holder, position == 0 ? item1 : item2);
                holder.infoPage.setTag(holder);
                return holder.infoPage;
        }

        return convertView;    }

    @Override
    public int getPagesCount() {
        return PAGES;
    }

    private void fillHolder(TrainersHolder holder, Trainer friend) {
        if (friend == null)
            return;
        Iterator<TextView> iViews = holder.interests.iterator();
        Iterator<String> iInterests = friend.getInterests().iterator();
        while (iViews.hasNext() && iInterests.hasNext())
            iViews.next().setText(iInterests.next());
        //holder.infoPage.setBackgroundColor(0x00CCFF33);
        holder.nickName.setText(friend.getLast_name());
    }
}
