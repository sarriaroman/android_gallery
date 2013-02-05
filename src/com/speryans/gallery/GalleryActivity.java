package com.speryans.gallery;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.speryans.gallery.adapters.PhotosAdapter;
import com.speryans.gallery.helpers.ImageLoader;
import com.speryans.gallery.helpers.ImageLoader.ImageListener;

public class GalleryActivity extends Activity implements ImageListener {

	private PhotosAdapter adapter;
	private GridView photosView;
	private ViewSwitcher viewSwitcher;
	private View gallery;
	private View photo;
	
	public static final int numCols = 4;
	public static final int padding = 8;
	
	public static final int small_quality = 5;
	public static final int big_quality = 50;
	
	private int current_photo = -1;

	private PhotoViewAttacher mAttacher;

	private ImageView photoView;
	private ImageButton closeBtn;
	private ImageLoader image_loader;
	private ImageButton nextBtn;
	private ImageButton previousBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.speryans.gallery.R.layout.container);
		
		viewSwitcher = ( ViewSwitcher ) this.findViewById(com.speryans.gallery.R.id.switcher );
		
		gallery = (View) this.findViewById(com.speryans.gallery.R.id.gallery_content);
		photo = (View) this.findViewById(com.speryans.gallery.R.id.photo_content);
		
		int iDisplayWidth = getResources().getDisplayMetrics().widthPixels;
		int iImageWidth = ( ( iDisplayWidth / numCols ) - ( padding * ( numCols / 2 ) ) );
		
		adapter = new PhotosAdapter(this, iImageWidth);
		
		photosView = (GridView) gallery.findViewById(com.speryans.gallery.R.id.photosView);

	    photosView.setColumnWidth( iImageWidth );
	    photosView.setStretchMode( GridView.STRETCH_COLUMN_WIDTH ) ; 
	    photosView.setHorizontalSpacing(padding);
	    photosView.setVerticalSpacing(padding);
	    
	    closeBtn = (ImageButton) photo.findViewById(com.speryans.gallery.R.id.closeBtn);
	    closeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
				viewSwitcher.showNext();
			}
	    	
	    });
	    
	    nextBtn = (ImageButton) photo.findViewById(com.speryans.gallery.R.id.nextBtn);
	    nextBtn.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				nextPhoto();
			}
	    	
	    });
	    
	    previousBtn = (ImageButton) photo.findViewById(com.speryans.gallery.R.id.previousBtn);
	    previousBtn.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				previousPhoto();
			}
	    	
	    });
		
	    String[] urls = this.getIntent().getStringArrayExtra("url");
		
		if( urls == null ) finish();
		if( urls.length == 0 ) finish();
		/*if( urls.length == 1 ) {
			onItemClicked(urls[0]);
			finish();
		} else {*/
			for( String url : urls ) {
				adapter.photos.add(url);
				Log.i("Gallery", url);
			}
			
			photosView.setAdapter(adapter);
		//}
		
		photosView.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				onItemClicked(position);
			}
			
		});
	}

	private void preparePhoto() {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		String image = adapter.photos.elementAt(current_photo);

		photoView = (ImageView) photo.findViewById(com.speryans.gallery.R.id.photoView);
		
		image_loader = new ImageLoader(this, com.speryans.gallery.R.drawable.transparent);
		image_loader.displayImage(image, photoView, GalleryActivity.big_quality, this);
		
		mAttacher = new PhotoViewAttacher(photoView);
	    
	    viewSwitcher.showNext();
	}
	
	private void nextPhoto() {
		current_photo++;
		if( current_photo >= adapter.getCount() ) current_photo = 0;
		
		String image = adapter.photos.elementAt(current_photo);
		
		image_loader.displayImage(image, photoView, GalleryActivity.big_quality, this);
	}
	
	private void previousPhoto() {
		current_photo--;
		if( current_photo < 0 ) current_photo = (adapter.getCount() - 1);
		
		String image = adapter.photos.elementAt(current_photo);
		
		image_loader.displayImage(image, photoView, GalleryActivity.big_quality, this);
	}
	
	public void onItemClicked( int crrt ) {
		current_photo = crrt;
		
		preparePhoto();
	}
	
	@Override
	public void imageLoaded(String url) {
		photoView.setVisibility(View.VISIBLE);
		mAttacher.update();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
}
