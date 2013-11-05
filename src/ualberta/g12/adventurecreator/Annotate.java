//****Extra Code used for annotation portion user story****

/*package ualberta.g12.adventurecreator;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;




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

//from: http://stackoverflow.com/questions/16557076/how-to-smoothly-move-a-image-view-with-users-finger-on-android-emulator
//TODO: put annotate image in Read Fragment
//***annotate image to be used in read fragment***
/*  final ImageView img = (ImageView) findViewById(R.id.annotateImage);

img.setOnTouchListener(new OnTouchListener()
{
    PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
    PointF StartPT = new PointF(); // Record Start Position of 'img'

    @SuppressLint("NewApi")
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


//XML for imageview of annotation
<ImageView
android:id="@+id/annotateImage"
android:layout_width="100dip"
android:layout_height="100dip"
android:layout_alignParentRight="true"
android:layout_alignTop="@+id/editText3"
android:layout_marginTop="21dp"
android:src="@android:drawable/alert_light_frame" />

//for activity on result code
*             if (x == 1) {
            ImageView view = (ImageView) findViewById(R.id.annotateImage);
            view.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
            }
*/