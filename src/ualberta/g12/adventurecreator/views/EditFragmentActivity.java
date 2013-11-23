
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

// Right now we are making Fragments here and also choice
// What i was thinking was maybe just make the general fragment here
// and have another screen to add choices then we could have list a listview type thing so can have a lot of choices
// and long clicking each one would let you edit/delete them 
// TODO discuss with the team
//

/**
 * Activity for editing a fragment. Will allow the author to add text,
 * illustrations, or choices for the current fragment. Upon button clicks, the
 * activity will save the current fragment information and take the user back to
 * the fragment list of the current story.
 */
public class EditFragmentActivity extends Activity implements FView<Fragment> {

    private int storyId, position, type, picturePosition;
    private TextView fragmentTitleTextView;
    private ListView fragmentPartListView;
    private FragmentPartAdapter adapter;
    private FragmentController fragmentController = AdventureCreator
            .getFragmentController();
    public static final int EDIT = 0;
    public static final int ADD = 1;
    private EditText editTitleText, idPageNumText;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final String TAG = "EditFragmentActivity";
    private OfflineIOHelper offlineHelper;
    private String mode, pictureMode;
    private StoryList storyList;
    private Story story;
    private int storyPos, fragPos;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_editor);

        // obtain the intent
        Intent editActIntent = getIntent();
        // TODO: what if mode is null?
        mode = "";
        mode = (String) editActIntent.getSerializableExtra("Mode");
        storyPos = (Integer) editActIntent.getSerializableExtra("StoryPos");
        // Not reliable when in view mode
        fragPos = (Integer) editActIntent.getSerializableExtra("FragmentPos");
        // get widget references
        fragmentPartListView = (ListView) findViewById(R.id.FragmentPartList);
        fragmentTitleTextView = (TextView) findViewById(R.id.fragmentTitleText);
        editTitleText = (EditText) findViewById(R.id.fragmentTitle);
        idPageNumText = (EditText) findViewById(R.id.idPageNum);

        setListClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: This should be in its own method
        
        // load save file
        storyList = AdventureCreator.getStoryList();
        story = storyList.getAllStories().get(storyPos);
        fragment = story.getFragments().get(fragPos);

        // Make sure we have at least one part if in edit mode
        if (mode.equals("Edit") == true && fragment.getDisplayOrder().size() == 0) {
            fragmentController.addEmptyPart(fragment);
        }

        // First load fragment parts as that is the same for both modes
        // Loads fragment parts (text, images, videos, sounds, etc)
        adapter = new FragmentPartAdapter(this, R.layout.activity_fragment_editor, fragment);
        fragmentPartListView.setAdapter(adapter);

        if (mode.equals("Edit")) {
            // don't want textview
            fragmentTitleTextView.setVisibility(View.GONE);

            // want the context menu for list parts
            registerForContextMenu(fragmentPartListView);

            // Loads title
            String title = fragment.getTitle();
            if (editTitleText != null) {
                if (title != null)
                    editTitleText.setText(title);
                else
                    editTitleText.setText("");
            }

        } else if (mode.equals("View")) {
            // don't want edit text views
            editTitleText.setVisibility(View.GONE);
            idPageNumText.setVisibility(View.GONE);

            // Loads title
            String title = fragment.getTitle();
            if (fragmentTitleTextView != null) {
                if (title != null)
                    fragmentTitleTextView.setText(title);
                else
                    fragmentTitleTextView.setText("Error - No title found");
            }
        } else {
            // Invalid mode
            Log.e(TAG, String.format("Invalid mode: %s", mode));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // we only want the menu when in edit mode
        if (mode.equals("Edit"))
            getMenuInflater().inflate(R.menu.fragment_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.add_option:
                return true;

            case R.id.save_fragment:
                // Save values
                if (mode.equals("Edit"))
                    saveFragment();

                // Leave activity
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Fragment model) {
        // TODO reload all fields based on new info from model

    }

    private void setListClickListener() {
        fragmentPartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (fragment.getDisplayOrder().get(position).equals("c")) {
                    if (mode.equals("Edit")) {
                        // TODO open edit choice activity

                    } else if (mode.equals("View")) {
                        // go to next fragment

                        // get the occurence number of the textSegment
                        int occurrence = 0;
                        for (int i = 0; i < position; i++) {
                            if (fragment.getDisplayOrder().get(i).equals("c"))
                                occurrence++;
                        }
                        int newFragPos = fragment.getChoices().get(occurrence)
                                .getLinkedToFragmentPos();

                        if (newFragPos == -1) {
                            // linked frag pos is invalid so don't use it
                            newFragPos = fragPos;
                        }

                        Intent intent = new Intent(EditFragmentActivity.this,
                                EditFragmentActivity.class);

                        // for (int i = 0 ; i <
                        // storyList.getAllStories().get(storyPos).getFragments().size();i++){
                        // if
                        // (storyList.getAllStories().get(storyPos).getFragments().get(i).equals(goToFrag))
                        // fragPos = i;
                        // }

                        intent.putExtra("Mode", "View");
                        intent.putExtra("StoryPos", storyPos);
                        intent.putExtra("FragmentPos", newFragPos);

                        // theExtras.putSerializable("nextFragment", goToFrag);
                        // System.out.println(goToFrag.getDisplayOrder().toString());
                        // System.out.println(goToFrag.getTitle());
                        startActivity(intent);
                        finish();

                    }
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        position = (int) info.id;

        CharSequence itemTitle = item.getTitle();
        if (itemTitle.equals("Insert Text")) {
            fragmentController.addTextSegment(fragment, "New text", position);

        } else if (itemTitle.equals("Insert Illustration")) {
            System.out.println("insert ill start");
            saveFragment();
            pictureMode = "Add";
            picturePosition = position;
            AddImage();

        } else if (itemTitle.equals("Edit")) {
            String type = fragment.getDisplayOrder().get(position);
            if (type.equals("t") || type.equals("e")) {
                RelativeLayout curLayout = new RelativeLayout(this);

                LayoutInflater inflater = (LayoutInflater) this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final PopupWindow editTextWindow = new PopupWindow(inflater.inflate(
                        R.layout.edit_text_seg_popup, null, true), fragmentPartListView.getWidth(),
                        400, true);

                final EditText editTextSegView = (EditText) editTextWindow.getContentView()
                        .findViewById(R.id.editTextSeg);
                if (type.equals("t")) {
                    int occurrence = 0;
                    for (int i = 0; i < position; i++) {
                        if (fragment.getDisplayOrder().get(i).equals("t"))
                            occurrence++;
                    }
                    editTextSegView.setText(fragment.getTextSegments().get(occurrence));
                }

                Button editTextSave = (Button) editTextWindow.getContentView().findViewById(
                        R.id.editTextSave);
                Button editTextCancel = (Button) editTextWindow.getContentView().findViewById(
                        R.id.editTextCancel);

                editTextSave.setOnClickListener(new EditTextSegOnClickListener(position) {
                    @Override
                    public void onClick(View v) {
                        String newText = editTextSegView.getText().toString();
                        fragmentController.deleteFragmentPart(fragment, this.getPosition());
                        fragmentController.addTextSegment(fragment, newText, this.getPosition());
                        fragmentPartListView.invalidateViews();
                        editTextWindow.dismiss();
                    }
                });

                editTextCancel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTextWindow.dismiss();
                    }
                });

                editTextWindow.showAtLocation(curLayout, Gravity.CENTER, 0, 0);
                editTextWindow.update(0, 0, fragmentPartListView.getWidth(), 400);

            } else if (type.equals("i")) {
                saveFragment();
                pictureMode = "Edit";
                picturePosition = position;
                AddImage();
            } else if (type.equals("c")) {

                // get the occurrence number of the choice
                int choicePos = 0;
                for (int i = 0; i < position; i++) {
                    if (fragment.getDisplayOrder().get(i).equals("c"))
                        choicePos++;
                }

                // go to edit choice activity
                Intent intent = new Intent(this, EditChoiceActivity.class);
                intent.putExtra("StoryPos", storyPos);
                intent.putExtra("FragmentPos", fragPos);
                intent.putExtra("ChoicePos", choicePos);
                startActivity(intent);
            }

        } else if (itemTitle.equals("Add Choice")) {
            // create, add and save new choice
            int choicePos = fragment.getChoices().size();
            Choice choice = new Choice();
            // fragment.addChoice(choice);
            fragmentController.addChoice(fragment, choice);
            saveFragment();

            // go to edit choice activity
            Intent intent = new Intent(this, EditChoiceActivity.class);
            intent.putExtra("StoryPos", storyPos);
            intent.putExtra("FragmentPos", fragPos);
            intent.putExtra("ChoicePos", choicePos);
            startActivity(intent);

        } else if (itemTitle.equals("Delete")) {
            fragmentController.deleteFragmentPart(fragment, position);
        }
        // Make sure the fragment isn't completely empty
        if (fragment.getDisplayOrder().size() == 0)
            fragmentController.addEmptyPart(fragment);

        // reset listview to display any changes
        // adapter.notifyDataSetChanged();
        fragmentPartListView.invalidateViews();
        return true;
    }

    /**
     * Allows the user to import an image from either the camera or gallery
     * through selection prompts. The image is put into a listview that is
     * displayed for the current fragment. The function branches to
     * "onActivityResult" which handles the image processing.
     */
    public void AddImage() {
        final CharSequence[] options = {
                "Take Photo", "Choose from Gallery", "Cancel"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(EditFragmentActivity.this);
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
            String picturePath = null;
            if (requestCode == 1) {

                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    Log.d(TAG, "path of image from camera" + picturePath + "");

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    // String path = f.getPath();

                    long picTime = System.currentTimeMillis();

                    String newPicName = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(picTime);

                    Log.d(TAG, "path of image from camera" + picturePath + "");
                    picturePath = f.getPath();

                    Log.d(TAG, "path of image from camera" + picturePath + "");

                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(picturePath, String.valueOf(picTime) + ".jpg");
                    Log.d(TAG, "path of image from camera" + picturePath + "");
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
                picturePath = c.getString(columnIndex);
                c.close();
                bitmap = (BitmapFactory.decodeFile(picturePath));
                Log.d(TAG, "path of image from gallery" + picturePath + "");
            }
            Log.d(TAG, "path of image END" + picturePath + "");
            if (picturePath != null) {
                if (pictureMode.equals("Add")) {
                    System.out.println("add ill start");
                    fragmentController.addIllustration(fragment, picturePath, position);
                } else if (pictureMode.equals("Edit")) {
                    fragmentController.deleteFragmentPart(fragment, position);
                    fragmentController.addIllustration(fragment, picturePath, position);
                }
                saveFragment();
                // fragmentPartListView.invalidateViews();
            } else
                Log.d(TAG, "picturePath returned is null");
        }
    }

    private void setTitleAndPageId() {

        String title = editTitleText.getText().toString();
        String idPageNum = idPageNumText.getText().toString();
        int idPage = -9;
        // try{
        // idPage = Integer.parseInt(idPageNum);
        // }catch(NumberFormatException e){
        // Log.d("Msg","There was a number format exception!");
        // }

        fragment.setTitle(title);
        fragment.setId(idPage);
    }

    private void saveFragment() {
        // make sure fragment does not have any empty parts
        fragmentController.removeEmptyPart(fragment);

        setTitleAndPageId();
        storyList.getAllStories().get(storyPos).getFragments().set(fragPos, fragment);
        offlineHelper = AdventureCreator.getOfflineIOHelper();
        offlineHelper.saveOfflineStories(storyList);
    }
}