package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import selmibenromdhane.sparta_v1.manager.Article;
import selmibenromdhane.sparta_v1.utils.Utils;


public class ClassesCustomAdapter extends ArrayAdapter<Article> {


	private int resourceId = 0;
	private LayoutInflater inflater;
	public Context mContext;

	public ClassesCustomAdapter(Context context, int resourceId, List<Article> mediaItems) {
		super(context, 0, mediaItems);
		this.resourceId = resourceId;
		this.mContext = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	//ViewHolder Design Pattern
	static class ViewHolder {
		public TextView textTitle, DescText;
		public ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		//Réutiliser les Views
		if (rowView == null) {
			rowView = inflater.inflate(resourceId, parent, false);
		}

		//Configuration du ViewHolder
		ViewHolder viewHolder = new ViewHolder();
		//viewHolder.image = (ImageView) rowView.findViewById(R.id.imgArticle);
	//	viewHolder.textTitle = (TextView) rowView.findViewById(R.id.titreArticle);
		Utils.changeFont(mContext.getAssets(), viewHolder.textTitle);
	//	viewHolder.DescText = (TextView) rowView.findViewById(R.id.descArticle);
		rowView.setTag(viewHolder);

		//Affecter les données aux Views
		ViewHolder holder = (ViewHolder) rowView.getTag();
		Article article = getItem(position);

//		holder.textTitle.setText(article.getTitre());
//		holder.DescText.setText(article.getDesc());
//		Picasso.with(mContext).load(article.getImage())
//				.into(holder.image);

		return rowView;
	}


	}