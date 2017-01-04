package selmibenromdhane.sparta_v1.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailsGallerieActivity;
import selmibenromdhane.sparta_v1.activity.GallerieActivity;
import selmibenromdhane.sparta_v1.activity.GridViewActivity;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.services.GPSService;
import selmibenromdhane.sparta_v1.services.getLocation;
import selmibenromdhane.sparta_v1.utils.CircleTransform;
import selmibenromdhane.sparta_v1.utils.Gallery;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<Gallery> items = new ArrayList<>();
    public ArrayList<String> url=new ArrayList();

    private Context ctx;




    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView photo;
        public TextView user_name;
        public ImageView bt_more;
        public ImageView photo_content;
        public ImageButton bt_like;
        public ImageButton bt_comment;

        public TextView nbLike;
        public TextView desc;
        public CardView rs;
        public TextView localisation;

        public Gallery currentPair;
        public ViewHolder(View v) {
            super(v);
         /*   v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Share Clicked", Snackbar.LENGTH_SHORT).show();
                    //Intent intent = new Intent(view.getContext(), DetailsCinemaActivity.class);
                    //intent.putExtra("cinema",currentPair);
                    //view.getContext().startActivity(intent);
                }
            });
            */
            photo = (ImageView) v.findViewById(R.id.ivUserProfil);
            bt_more = (ImageView) v.findViewById(R.id.btnMore);
            photo_content = (ImageView) v.findViewById(R.id.ivFeedCenter);
            bt_like = (ImageButton) v.findViewById(R.id.btnLike);
            bt_comment = (ImageButton) v.findViewById(R.id.btnComments);
            user_name=(TextView)v.findViewById(R.id.text_name);
            desc=(TextView)v.findViewById(R.id.desc);
            nbLike=(TextView)v.findViewById(R.id.nbLike);
            rs=(CardView)v.findViewById(R.id.card_view);
            localisation=(TextView)v.findViewById(R.id.adresse);



        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FeedAdapter(Context ctx, ArrayList<Gallery> items) {
        this.ctx = ctx;
        this.items = items;
        //this. url.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");

    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      //  YoYo.with(Techniques.FadeIn).playOn(holder.rs);
        YoYo.with(Techniques.FadeInUp).duration(2000).playOn(holder.rs);
       holder.localisation.setText("ddd");
        holder.currentPair = items.get(position);
        Picasso.with(ctx).load(holder.currentPair.getPhoto()).into(holder.photo_content);
        Picasso.with(ctx).load(holder.currentPair.getPhotoUser()).transform(new CircleTransform()).into(holder.photo);
        holder.user_name.setText(holder.currentPair.getUser_name());
        holder.nbLike.setText(holder.currentPair.getNbLike());
        holder.desc.setText(holder.currentPair.getPosted());
        final ShareDialog shareDialog = new ShareDialog((Activity) ctx);

        holder.bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(holder.currentPair.getPhoto());
                if (ShareDialog.canShow(ShareLinkContent.class))
                {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder().setContentTitle("D")
                            .setImageUrl(Uri.parse(holder.currentPair.getPhoto())).setContentDescription("Find your favorite Cinema p.getNom( in the best mobile application : googleplay.cinema")
                            .setContentUrl(Uri.parse("https://www.google.fr/")).build();
                    shareDialog.show(linkContent);
                }
            }
        });
       // String p=holder.currentPair;
       // Picasso.with(ctx).load(R.drawable.img_feed_center_1).resize(80, 80).into(holder.photo);
        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "photo", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx, GridViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);

            }
        });
        holder.bt_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nb=holder.nbLike.getText().toString();
                int n=Integer.parseInt(nb)+1;
                holder.nbLike.setText(String.valueOf(n));

                Picasso.with(ctx).load(R.drawable.ic_heart_red).into(holder.bt_like);
                //Snackbar.make(view,(Integer.parseInt(nb)+1)., Snackbar.LENGTH_SHORT).show();
             //   holder.nbLike.setText(Integer.valueOf(holder.nbLike.getText().toString())+1);

            }
        });
        /*
        holder.text_name.setText(p.getNom());
        Picasso.with(ctx).load(p.getLogoCine()).into(holder.photo_content);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)

        {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("likeCine");
            myRef.child(user.getUid()+p.getOwner()).addListenerForSingleValueEvent(
                    new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue()!=null)
                            {
                                Picasso.with(ctx).load(R.drawable.ic_heart_red).into((ImageView) holder.bt_like);
                                holder.bt_like.setTag("like");}

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }





        holder.bt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), DetailsCinemaActivity.class);
                intent.putExtra("cinema",p);
                view.getContext().startActivity(intent);
            }
        });



        holder.bt_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    if( String.valueOf(view.findViewById(R.id.bt_like).getTag()).equals("dislike"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("likeCine");
                        likeCinema l = new likeCinema(p.getOwner(),user.getUid());

                        myRef.child(l.getUid()+l.getIdCinema()).setValue(l);
                        Picasso.with(ctx).load(R.drawable.ic_heart_red).into((ImageView) view.findViewById(R.id.bt_like));
                        view.findViewById(R.id.bt_like).setTag("like");
                        Snackbar.make(view, "Like Clicked", Snackbar.LENGTH_SHORT).show();
                    } else
                    {
                        likeCinema l = new likeCinema(p.getOwner(),user.getUid());
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("likeCine");
                        myRef.child(l.getUid()+l.getIdCinema()).removeValue();

                        view.findViewById(R.id.bt_like).setTag("dislike");
                        Picasso.with(ctx).load(R.drawable.ic_heart_outline_grey).into((ImageView) view.findViewById(R.id.bt_like));
                        Snackbar.make(view, "Like Clicked", Snackbar.LENGTH_SHORT).show();
                    }
                }else
                {
                    Intent i =new Intent(ctx, Login_video.class);
                    ctx.startActivity(i);
                }
            }
        });

        holder.bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentActivity activity = (FragmentActivity) (ctx);
                FragmentManager fm = activity.getSupportFragmentManager();
                CommentFragment alertDialog = new CommentFragment();
                Bundle b=new Bundle();
                b.putSerializable("cinema",p);
                alertDialog.setArguments(b);
                alertDialog.show(fm, "fragment_alert");

                Snackbar.make(view, "Comment Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
        holder.bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    SharePhoto photo = new SharePhoto.Builder().setBitmap(MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), Uri.parse(p.getLogoCine()))).build();
                    SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
                    ShareDialog shareDialog = new ShareDialog((Activity) ctx);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Snackbar.make(view, "Share Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });



*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}