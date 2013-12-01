
package ualberta.g12.adventurecreator.views;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

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
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;

/**
 * Activity for viewing a fragment. User can follow choices and will be taken to
 * the next fragment.
 */
public class FragmentViewActivity extends Activity implements FView<Fragment> {

    private TextView fragmentTitleTextView;
    private ListView fragmentPartListView;
    private FragmentPartAdapter adapter;
    private FragmentController fragmentController;
    // private static final String TAG = "FragmentViewActivity";
    private Fragment fragment;
    ImageView viewImage, viewImage2, viewImage3;
    public int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewer);

        fragmentController = AdventureCreator.getFragmentController();

        // obtain the intent
        Intent viewFragIntent = getIntent();
        fragment = (Fragment) viewFragIntent.getSerializableExtra("Fragment");

        // get widget references
        fragmentPartListView = (ListView) findViewById(R.id.fragmentViewPartList);
        fragmentTitleTextView = (TextView) findViewById(R.id.fragmentTitleText);

        setListClickListener();

        viewImage = (ImageView) findViewById(R.id.viewImage);
        viewImage2 = (ImageView) findViewById(R.id.viewImage2);
        viewImage3 = (ImageView) findViewById(R.id.viewImage3);

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

        // TODO: This should be in its own method

        // First load fragment parts as that is the same for both modes
        // Loads fragment parts (text, images, videos, sounds, etc)
        adapter = new FragmentPartAdapter(this, R.layout.activity_fragment_editor, fragment);
        fragmentPartListView.setAdapter(adapter);

        // Loads title
        if (fragmentTitleTextView != null)
            fragmentTitleTextView.setText(fragment.getTitle());
    }

    @Override
    public void update(Fragment model) {
        // TODO reload all fields based on new info from model

    }

    private void setListClickListener() {
        fragmentPartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment goToFrag = fragmentController.getLinkedToFragmentOfChoice(fragment,
                        position);
                if (goToFrag == null || fragment.getChoices().get(position).getisRandom() == true) {
                    boolean notSamePos = false;
                    while (notSamePos == false) {
                        Random random = new Random();
                        int max = fragment.getChoices().size() - 1;
                        int randPos = random.nextInt((max - 0) + 1) + 0;
                        if (position != randPos) {
                            goToFrag = fragmentController.getLinkedToFragmentOfChoice(fragment,
                                    randPos);
                            notSamePos = true;
                        }
                    }
                }
                if (goToFrag != null) {
                    Intent intent = new Intent(FragmentViewActivity.this,
                            FragmentViewActivity.class);
                    intent.putExtra("Fragment", goToFrag);
                    startActivity(intent);
                    finish();
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

<<<<<<< HEAD
    /**
     * Function that allows the user to add an illustration as an annotation 
     */
public void AddImage() {
    final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
    
    AlertDialog.Builder builder = new AlertDialog.Builder(FragmentViewActivity.this);
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
=======
    public void addImage() {
        final CharSequence[] options = {
                "Take Photo", "Choose from Gallery", "Cancel"
        };
>>>>>>> 7e0abdd4e12a2666558561fd63aabef9a8d29de8

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
                    if (x == 0) {
                        viewImage.setImageBitmap(bitmap);
                    }
                    if (x == 1) {
                        viewImage2.setImageBitmap(bitmap);
                    }
                    if (x == 2) {
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
                String[] filePath = {
                    MediaStore.Images.Media.DATA
                };
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath
                        + "");

                if (x == 0) {
                    viewImage.setImageBitmap(thumbnail);
                }
                if (x == 1) {
                    viewImage2.setImageBitmap(thumbnail);
                }
                if (x == 2) {
                    viewImage3.setImageBitmap(thumbnail);
                }

            }
        }
    }
}
