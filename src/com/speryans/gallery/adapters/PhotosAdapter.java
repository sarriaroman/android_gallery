package com.speryans.gallery.adapters;

import java.util.Vector;

import org.json.JSONObject;

import com.speryans.gallery.GalleryActivity;
import com.speryans.gallery.R;
import com.speryans.gallery.helpers.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotosAdapter extends BaseAdapter {
	private Context mContext;
	
	private ImageLoader il;
	
	public Vector<String> photos = new Vector<String>();
	public Vector<View> views = new Vector<View>();

	private int size = 85;
	
    public PhotosAdapter(Context c, int size) {
        mContext = c;
        
        il = new ImageLoader(mContext, R.drawable.transparent);
        
        this.size = size;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return photos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return photos.elementAt(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = null;
		if( convertView == null ) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(this.size, this.size));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}
		
		String obj = photos.elementAt(position);
			
		//ImageView img = (ImageView) convertView.findViewById( R.id.imageView );
		il.displayImage(obj, imageView, GalleryActivity.small_quality);
			
		return imageView;
	}

}
