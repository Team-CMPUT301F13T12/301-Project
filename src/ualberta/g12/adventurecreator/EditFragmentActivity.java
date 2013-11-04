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
import android.widget.TextView;

import java.io.File;

public class EditFragmentActivity extends Activity {

    Uri imageFileUri;
    private static final int InsertAnnotate = Menu.FIRST;
    private static final int InsertPicture = Menu.FIRST +2;
    
    public int x = 0;
    
        //button for ButtonImage if we want to use it
//	Button addImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_editor);

		
		//Image Button to insert illustration if we want to use image buttons instead
	/*	   ImageButton button = (ImageButton) findViewById(R.id.imageButton);
	        OnClickListener listener = new OnClickListener() {
	            public void onClick(View v){
	                AddImage();
	            }
	        };
	        
	        button.setOnClickListener(listener);*/
	     
	        
	        //from: http://stackoverflow.com/questions/16557076/how-to-smoothly-move-a-image-view-with-users-finger-on-android-emulator
	        
	        final ImageView img = (ImageView) findViewById(R.id.annotateImage);
	        
	        img.setOnTouchListener(new OnTouchListener()
	        {
	            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
	            PointF StartPT = new PointF(); // Record Start Position of 'img'

	            public boolean onTouch(View v, MotionEvent event)
	            {
	                int eid = event.getAction();
	                switch (eid)
	                {
	                    case MotionEvent.ACTION_MOVE :
	                        PointF mv = new PointF( event.getX() - DownPT.x, event.getY() - DownPT.y);
	                        img.setX((int)(StartPT.x+mv.x));
	                        img.setY((int)(StartPT.y+mv.y));
	                        StartPT = new PointF( img.getX(), img.getY() );
	                        break;
	                    case MotionEvent.ACTION_DOWN :
	                        DownPT.x = event.getX();
	                        DownPT.y = event.getY();
	                        StartPT = new PointF( img.getX(), img.getY() );
	                        break;
	                    case MotionEvent.ACTION_UP :
	                        // Nothing have to do
	                        break;
	                    default :
	                        break;
	                }
	                return true;
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_editor, menu);
		
		//create option for annotating on menu bar
		super.onCreateOptionsMenu(menu);
        menu.add(0, InsertAnnotate, 0, R.string.annotate);
        menu.add(0, InsertPicture, 0, R.string.InsertPic);
		return true;
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
	 private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	    
	    public void AddImage() {
	        
	         x = 0;
	        
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
	                ImageView view = (ImageView) findViewById(R.id.imageButton);
	                view.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
	                }
	                if (x == 1) {
	                    ImageView view = (ImageView) findViewById(R.id.annotateImage);
	                    view.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
	                    }
	            }
	            
	        }
	    }
	    
	    //TODO: add in multiple annotations 
	    public void AddAnnotation() {
	        
	        x = 1;
	        
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        
	        String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tmp";
	        File folderF = new File(folder);
	        if (!folderF.exists()) {
	            folderF.mkdir();
	        }
	        
	        String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";
	        File imageFile = new File(imageFilePath);
	        imageFileUri = Uri.fromFile(imageFile);
	        
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	        
	    } 
}

