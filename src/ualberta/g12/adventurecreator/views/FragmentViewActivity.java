
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/**
 * Activity for viewing a fragment. User can follow choices and will be taken to
 * the next fragment. Users can also add annotations.
 **/
public class FragmentViewActivity extends Activity implements FView<Fragment> {

    private TextView fragmentTitleTextView;
    private ListView fragmentPartListView;
    private FragmentPartAdapter adapter;
    private StoryList storyList;
    private Story story;
    private Fragment fragment;
    ImageView viewImage, viewImage2, viewImage3;
    public int storyPos, fragPos, x = 0;
    private static final boolean DEBUG = true;
    private static final String TAG = "FragmentViewActivity";

    // Controllers
    private StoryListController storyListController;
    private StoryController storyController;
    private FragmentController fragmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewer);

        storyListController = AdventureCreator.getStoryListController();
        storyController = AdventureCreator.getStoryController();
        fragmentController = AdventureCreator.getFragmentController();
        
        // obtain the intent
        Intent viewFragIntent = getIntent();
        storyPos = (Integer) viewFragIntent.getSerializableExtra("StoryPos");
        storyList = AdventureCreator.getStoryList();
        story = storyList.getStoryAtPos(storyPos);
        
        // get widget references
        fragmentPartListView = (ListView) findViewById(R.id.fragmentViewPartList);
        fragmentTitleTextView = (TextView) findViewById(R.id.fragmentTitleText);
        viewImage = (ImageView) findViewById(R.id.viewImage);
        viewImage2 = (ImageView) findViewById(R.id.viewImage2);
        viewImage3 = (ImageView) findViewById(R.id.viewImage3);

        // set fragment to first fragment in story
        fragPos = story.getStartFragPos();
        fragment = story.getFragmentAtPos(fragPos);
        
        if (DEBUG)
            Log.d(TAG, "start pos " + fragPos);

        // show everything
        update();

        // set click listeners
        setListClickListener();

        viewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 0;
                addImage();
            }
        });

        viewImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                addImage();
            }
        });

        viewImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                addImage();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragment.addView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragment.deleteView(this);
    }
    
    @Override
    public void update(Fragment model) {
        update();
    }

    private void update() {
        // TODO reload all fields based on new info from model

        // Loads title
        if (fragmentTitleTextView != null)
            fragmentTitleTextView.setText(fragment.getTitle());

        // Loads fragment parts (text, images, videos, sounds, etc)
        adapter = new FragmentPartAdapter(this, R.layout.activity_fragment_editor, fragment);
        fragmentPartListView.setAdapter(adapter);
        
        Log.d(TAG, "UPDATE");
        Log.d(TAG, fragment.getAnnotations().toString());
        Log.d(TAG, "size" +fragment.getAnnotations().size());
        File file;
        for(int i=0; i<fragment.getAnnotations().size(); i++){
            file = new File(fragment.getAnnotations().get(i));
            Log.d(TAG, "" +file.getAbsoluteFile());
            if (file.exists()){
                Log.d(TAG, "existis");
                if(i==0)
                    viewImage.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                if(i==1)
                    viewImage2.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                if(i==2)
                    viewImage3.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        }
    }

    private void setListClickListener() {
        fragmentPartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentPart part = fragment.getParts().get(position);
                int goToFragPos = part.getChoice().getLinkedToFragmentPos();

                /* get the directed to fragment */
                Fragment goToFrag;
                if (goToFragPos != -1)
                    goToFrag = story.getFragmentAtPos(goToFragPos);
                else
                    goToFrag = null;

                /* if choice is random, do random */
                if (part.getChoice().getisRandom()) {
                    goToFragPos = fragPos;
                    int options = story.getFragments().size();
                    if (options > 1) {
                        while (goToFragPos == fragPos) {
                            Random random = new Random();
                            goToFragPos = random.nextInt(options);
                        }
                        goToFrag = story.getFragmentAtPos(goToFragPos);
                    }
                }

                if (goToFrag != null) {
                    fragment = goToFrag;
                    fragPos = goToFragPos;
                    update();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_part_menu, menu);
    }  
    
    /**
     * Allows the user to add a picture from the gallery or take a new picture
     * and add it as an annotation.
     */
    public void addImage() {
        final CharSequence[] options = {
                "Take Photo", "Choose from Gallery", "Cancel"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(FragmentViewActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(),
                            "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
            Bitmap bitmap = null;
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

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
                String[] filePath = {
                        MediaStore.Images.Media.DATA
                };
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                bitmap = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath
                        + "");
            }
            
            if (bitmap != null) {
                // following line modified from
                // https://groups.google.com/forum/#!topic/android-developers/YjGcve7s5CQ
                // by Derek
                CharSequence appName = this.getResources().getText(
                        this.getResources().getIdentifier("app_name", "string",
                                this.getPackageName()));

                File folder = new File(Environment.getExternalStorageDirectory().toString(),
                        appName.toString());
                File storyFolder = new File(folder.getAbsolutePath(), Integer.toString(story
                        .getId()));
                boolean folderExists = true; // assume true

                if (!storyFolder.exists()) {
                    folderExists = storyFolder.mkdirs();
                }
                if (folderExists) {
                    // once folder exists finish creating picturePath
                    long picTime = System.currentTimeMillis();
                    String newPicName = "a" + new SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.US)
                            .format(picTime)
                            + ".jpg";
                    File file = new File(storyFolder.getAbsolutePath(), newPicName);
                    String picturePath = file.getAbsolutePath();

                    // then write the picture to picturePath
                    try {
                        OutputStream outFile = new FileOutputStream(file);
                        // TODO decide on quality size and figure out how
                        // to decrease picture dimensions?
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (DEBUG)
                        Log.d(TAG, "path of image END" + picturePath + "");
                    
                    // finish by updating the fragment part
                    fragmentController.setAnnotation(fragment, x, picturePath);

                    saveFragment();
                } else {
                    // unable to create folder
                }
            }
        }
    }
    private void saveFragment() {        
        storyController.setFragmentAtLocation(story, fragPos, fragment);
        storyListController.saveOfflineStories(storyList);
        
        //storyList.getStoryAtPos(storyPos)
    }
}
