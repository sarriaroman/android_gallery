package com.speryans.gallery;

import com.speryans.gallery.helpers.ImageLoader;
import com.speryans.gallery.helpers.ImageLoader.ImageListener;

import uk.co.senab.photoview.PhotoViewAttacher;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotoActivity extends Activity implements ImageListener {

	private PhotoViewAttacher mAttacher;

	private ImageView photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(com.speryans.gallery.R.layout.activity_photo);
		
		String image = this.getIntent().getStringExtra("url");

		photo = (ImageView) findViewById(com.speryans.gallery.R.id.photoView);
		
		ImageLoader image_loader = new ImageLoader(this, com.speryans.gallery.R.drawable.transparent);
		image_loader.displayImage(image, photo, 80, this);
		
		mAttacher = new PhotoViewAttacher(photo);
		
		// Just hide the screen controls
		((LinearLayout) this.findViewById(com.speryans.gallery.R.id.fullscreen_content_controls)).setVisibility(View.GONE);
	}

	@Override
	public void imageLoaded(String url) {
		photo.setVisibility(View.VISIBLE);
		mAttacher.update();
	}
}
