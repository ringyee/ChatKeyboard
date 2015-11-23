package cn.hadcn.keyboard.media;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.hadcn.keyboard.R;


public class MediaGridAdapter extends BaseAdapter {
	private ArrayList<MediaBean> mediaModels;
	Context mContext;

    /**
     * MediaGridAdapter
     * @param context context
     * @param mediaModels data
     */
	public MediaGridAdapter(Context context, ArrayList<MediaBean> mediaModels ) {
        this.mContext = context;
		this.mediaModels = mediaModels;
	}

	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder viewHolder;
		if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.media_item, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
        } else {
			viewHolder = (ViewHolder)convertView.getTag();
		}

		viewHolder.ivImage.setImageDrawable(ContextCompat.getDrawable(mContext, getItem(position).getDrawableId()));

        viewHolder.tvText.setText(getItem(position).getText());

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                getItem(position).getMediaListener().onMediaClick(getItem(position).getId());
			}
		});

		return convertView;
	}

	static class ViewHolder {
		public ImageView ivImage;
		public TextView tvText;

		public ViewHolder(View view) {
			ivImage = (ImageView)view.findViewById(R.id.media_item_image);
			tvText = (TextView)view.findViewById(R.id.media_item_text);
		}
	}
	
	@Override
	public int getCount() {		
		return mediaModels.size();
	}
	
	@Override
	public MediaBean getItem(int position) {
		return mediaModels.get(position);
	}
	
	@Override
	public long getItemId(int position) {		
		return position;
	}
}
