package ualberta.g12.adventurecreator;



import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class EditFragmentActivity extends Activity {

    Uri imageFileUri;
    private static final int InsertPhoto = Menu.FIRST;
    private static final int InsertGallery = Menu.FIRST + 2;
    private static final int InsertAnnotate = Menu.FIRST + 3;
   
	Button addImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_editor);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_editor, menu);
		
		//create option for annotating on menu bar
		super.onCreateOptionsMenu(menu);
		menu.add(0, InsertPhoto, 0, R.string.InsertFromPhoto);
		menu.add(0, InsertGallery, 0, R.string.InsertFromGallery);
        menu.add(0, InsertAnnotate, 0, R.string.annotate);
		return true;
	}

	 public boolean onMenuItemSelected(int featureId, MenuItem item) 
	    {
	        switch(item.getItemId()) 
	        {
	            case InsertPhoto:         //if insert photo is selected
	                useCamera(); 
	                return true;
	                
	            case InsertGallery:         //if insert photo is selected
	                useGallery(); 
	                return true;
	                
	            }
	        return super.onMenuItemSelected(featureId, item);
	    }
	 private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	 
     public void useCamera() {

         // From Camera Test Lab
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
             startActivityForResult(CameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

     }
     
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
             if (resultCode == RESULT_OK) {
                 ImageView view = (ImageView) findViewById(R.id.imageView);
                 view.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
             }
         }
         
     }
     
     //*****May want to use later, used if statements to choose gallery or camera...leave for now******
     /*
                // From: http://stackoverflow.com/q/16391124/1684866
    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT,null);
     galleryIntent.setType("image/*");
     galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
     
     Intent chooser = new Intent(Intent.ACTION_CHOOSER);   
     chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);      
     chooser.putExtra(Intent.EXTRA_TITLE, "Select Illustration From");
     
     Intent[] intentArray =  {CameraIntent}; 
     chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
     startActivityForResult(chooser,0); */
     
     
     
     //NOT FINISHED! only opens up gallery
     public void useGallery() {

         
         // From Camera Test Lab
         Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT,null);
         galleryIntent.setType("image/*");
         galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
         
         
         startActivityForResult(galleryIntent,0);
     }
	
}

