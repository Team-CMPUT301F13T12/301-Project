package ualberta.g12.adventurecreator;


import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import java.io.File;

public class EditFragmentActivity extends Activity {

    Uri imageFileUri;
    
    //not using action bar right now
    //private static final int InsertAnnotate = Menu.FIRST;
    //private static final int InsertPicture = Menu.FIRST +2;
    
    public int x = 0;
    
    
	Button addImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_editor);

		
		   ImageButton button = (ImageButton) findViewById(R.id.imageButton);
	        OnClickListener listener = new OnClickListener() {
	            public void onClick(View v){
	                x = 0;
	                AddImage();
	            }
	        };
	        
	        ImageButton button2 = (ImageButton) findViewById(R.id.imageButton2);
            OnClickListener listener2 = new OnClickListener() {
                public void onClick(View v){
                    x = 1;
                    AddImage();
                }
            };
            
            ImageButton button3 = (ImageButton) findViewById(R.id.imageButton3);
            OnClickListener listener3 = new OnClickListener() {
                public void onClick(View v){
                    x = 2;
                    AddImage();
                }
            };
	        
	        button.setOnClickListener(listener);
            button2.setOnClickListener(listener2);
            button3.setOnClickListener(listener3);
         
	     
	        
	}
//no menu actions right now
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_editor, menu);
		
	}

	 public boolean onMenuItemSelected(int featureId, MenuItem item) 
	    {
	        switch(item.getItemId()) 
	        {
                case InsertAnnotate:         //if insert annotation is selected
                    AddAnnotation(); 
                    return true;
                    
                case InsertPicture:         //if insert annotation is selected
                    AddImage(); 
                    return true;
	                
	            }
	        return super.onMenuItemSelected(featureId, item);
	        
	    }
	    */
	 private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	    
	    public void AddImage() {
	        
	           // From: http://stackoverflow.com/q/16391124/1684866
	        //*****Gallery Intent to save image      
	        //NOT FINISHED! only opens up gallery***
	         Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT,null);
	         galleryIntent.setType("image/*");
	         galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
	        //***end Gallery intent**** 
	        
	      
	         //*****Camera intent to save image *****
	         Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            
	            String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
	            File folderF = new File(folder);
	            if (!folderF.exists()) {
	                folderF.mkdir();
	        }     
	        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
	        File imageFile = new File(imageFilePath);
	        imageFileUri = Uri.fromFile(imageFile);
	        CameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	        //*********/End Camera intent******
	        
	        //Intent for chooser for image resource 
	         Intent chooser = new Intent(Intent.ACTION_CHOOSER);   
	         chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);      
	         chooser.putExtra(Intent.EXTRA_TITLE, "Select Illustration From");
	         
	         Intent[] intentArray =  {CameraIntent}; 
	         chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
	         startActivityForResult(chooser,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);  
	        
	    }
	   
	    //Loads camera image onto Allocated View
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	          
	        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	            if (resultCode == RESULT_OK) {
	                if (x == 0) {
	                ImageButton button = (ImageButton) findViewById(R.id.imageButton);
	                button.setScaleType(ScaleType.CENTER_INSIDE);
	                button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
	                }
	                if (x == 1) {
	                    ImageButton button = (ImageButton) findViewById(R.id.imageButton2);
	                    button.setScaleType(ScaleType.CENTER_INSIDE);
	                    button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
	                    }
	                if (x == 2) {
                        ImageButton button = (ImageButton) findViewById(R.id.imageButton3);
                        button.setScaleType(ScaleType.CENTER_INSIDE);
                        button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
                        }
	            }
	            
	        }
	    }
}

