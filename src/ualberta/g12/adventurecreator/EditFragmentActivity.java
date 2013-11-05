package ualberta.g12.adventurecreator;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class EditFragmentActivity extends Activity {

    Uri imageFileUri;
	ImageView viewImage,viewImage2, viewImage3;

    //not using action bar right now
    //private static final int InsertAnnotate = Menu.FIRST;
    //private static final int InsertPicture = Menu.FIRST +2;
    
    public int x = 0;
    
    
	Button addImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_editor);

	     
	        
        viewImage=(ImageView)findViewById(R.id.viewImage);
        viewImage2=(ImageView)findViewById(R.id.viewImage2);
        viewImage3=(ImageView)findViewById(R.id.viewImage3);
        
        viewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	x=0;
            	AddImage();
            }
        });
        
        viewImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	x=1;
            	AddImage();
            }
        });
        
        viewImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	x=2;
            	AddImage();
            }
        });
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
	// private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	    
	    public void AddImage() {
	        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
	        
	        AlertDialog.Builder builder = new AlertDialog.Builder(EditFragmentActivity.this);
	        builder.setTitle("Add Photo!");
	        builder.setItems(options, new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int item) {
	                if (options[item].equals("Take Photo"))
	                {
	                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
	                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
	                    startActivityForResult(intent, 1);
	                }
	                else if (options[item].equals("Choose from Gallery"))
	                {
	                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	                    startActivityForResult(intent, 2);
	 
	                }
	                else if (options[item].equals("Cancel")) {
	                    dialog.dismiss();
	                }
	            }
	        });
	        builder.show();
	    }
	 
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if (resultCode == RESULT_OK) {
	            if (requestCode == 1) {
	                File f = new File(Environment.getExternalStorageDirectory().toString());
	                for (File temp : f.listFiles()) {
	                    if (temp.getName().equals("temp.jpg")) {
	                        f = temp;
	                        break;
	                    }
	                }
	                try {
	                    Bitmap bitmap;
	                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	 
	                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
	                            bitmapOptions); 
	                   if(x==0){
	                    viewImage.setImageBitmap(bitmap);
	                   } 
	                   if(x==1){
	                    viewImage2.setImageBitmap(bitmap);
	                   }
	                   if(x==2){
	                       viewImage3.setImageBitmap(bitmap);
	                   }
	                   
	                    String path = android.os.Environment
	                            .getExternalStorageDirectory()
	                            + File.separator
	                            + "Phoenix" + File.separator + "default";
	                    f.delete();
	                    OutputStream outFile = null;
	                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
	                    try {
	                        outFile = new FileOutputStream(file);
	                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
	                        outFile.flush();
	                        outFile.close();
	                    } catch (FileNotFoundException e) {
	                        e.printStackTrace();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            } else if (requestCode == 2) {
	 
	                Uri selectedImage = data.getData();
	                String[] filePath = { MediaStore.Images.Media.DATA };
	                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
	                c.moveToFirst();
	                int columnIndex = c.getColumnIndex(filePath[0]);
	                String picturePath = c.getString(columnIndex);
	                c.close();
	                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
	                Log.w("path of image from gallery......******************.........", picturePath+"");
	                
	                if(x==0){
	                    viewImage.setImageBitmap(thumbnail);
	                } 
	                if(x==1){
	                    viewImage2.setImageBitmap(thumbnail);	
	                }
	                if(x==2){
	                    viewImage3.setImageBitmap(thumbnail);
	                }
	                
	                
	            }
	        }
	    }   
	}
	 
