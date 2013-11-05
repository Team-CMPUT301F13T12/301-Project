
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

// Right now we are making Fragments here and also choice
// What i was thinking was maybe just make the general fragment here
// and have another screen to add choices then we could have list a listview type thing so can have a lot of choices
// and long clicking each one would let you edit/delete them 
// TODO discuss with the team
//
public class EditFragmentActivity extends Activity implements FView<Fragment> {

    private Button addImage;
    private Button makeOrSave, editTextSave;
    private Fragment fragment;
    private FragmentController fragmentController;
    private int storyId;
    private TextView fragmentTitleTextView;
    private ListView fragmentPartListView;
    private FragmentPartAdapter adapter;
    private PopupWindow editTextWindow;
    private LinearLayout editTextLayout;
    private EditText editTextSegView;
    private int type;
    public static final int EDIT = 0;
    public static final int ADD = 1;
    private EditText titleText;
    private EditText idPageNumText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_editor);

        // obtain the intent
        Intent editActIntent = getIntent();
        Bundle bundledExtras = editActIntent.getExtras();
        String editType = bundledExtras.getString("EditType");
        titleText = (EditText) findViewById(R.id.fragmentTitle);
        idPageNumText = (EditText) findViewById(R.id.idPageNum);
        if (editType.equals("Edit") == false){
            // TODO: Load our fragment from the story model using the id given to us as an extra ( if we are editing an existing fragment)
        	type = ADD;
        	
            storyId  = bundledExtras.getInt("storyId");
            Log.d("This",String.format("The story i got  was: %d", storyId));
            
            // load title , id/page number , fragment description, choices
            
        }
        else{
        	type = EDIT;
        }
        
        // else then we are adding a new fragment dont need to load stuff

        //type = EDIT;
        
        storyId  = bundledExtras.getInt("storyId");
        if (type == EDIT){
        	StoryList sl = AdventureCreatorApplication.getStoryList();
        	Story story = sl.getStoryById(storyId);
        	Log.d("This",String.format("There was no story with id: %d", storyId));
        	List<Fragment>fragmentList = story.getFragments();
        	int pos = bundledExtras.getInt("pos");
        	Fragment fragment = fragmentList.get(pos);
        	titleText.setText(fragment.getTitle());
        	//idPageNumText.setText(String.format("%d",fragment.getId()));
        	String idString = (new Integer(fragment.getId())).toString();
        	Log.d("This",String.format("There was no story with id: %d", fragment.getId()));
        	idPageNumText.setText(idString);
        	
        }
        
        // TODO: Set the fragmentController to our Fragment

        /* for testing, will delete later -Lindsay */
        //FragmentController fragcont = new FragmentController();
        fragment = new Fragment();
        FragmentController.addTextSegment(fragment, "part1");
        FragmentController.addTextSegment(fragment,"part2");
        FragmentController.addTextSegment(fragment,"part number 3 which is rather long because we woud like to test text wrapping");
        FragmentController.addEmptyPart(fragment);
        if (editType.equals("Edit") == true && fragment.getDisplayOrder().size()==0){
            FragmentController.addEmptyPart(fragment);
        }
        // TODO: Load our fragment into view
        //get widget references
        fragmentPartListView = (ListView) findViewById(R.id.FragmentPartList);
        //fragmentTitleTextView = (TextView) findViewById(R.id.fragmentTitle);

        //Loads title
        //String title = fragment.getTitle();
        //if (fragmentTitleTextView != null){
        //    if (title != null)
        //        fragmentTitleTextView.setText(title);
        //    else
        //        fragmentTitleTextView.setText("Title Here");  //should this go here? -Lindsay
        //}

        //Loads fragment parts (text, images, videos, sounds, etc
        adapter = new FragmentPartAdapter(
                this, R.layout.activity_fragment_editor, fragment);
        fragmentPartListView.setAdapter(adapter);

        registerForContextMenu(fragmentPartListView);

        // TODO: Set up on clickers
        setUpOnClickListeners();

    }

    private void setUpOnClickListeners() {

        /* 
         * Should not need the add illustration button anymore
         * I have copied the code to the insert Illustration
         * menu option at the bottom of this file.
         */
        addImage = (Button) findViewById(R.id.buttonAddIll);
        addImage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // From: http://stackoverflow.com/q/16391124/1684866
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
                galleryIntent.setType("image/*");
                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                Intent chooser = new Intent(Intent.ACTION_CHOOSER);
                chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
                chooser.putExtra(Intent.EXTRA_TITLE, "Select Illustration From");

                Intent[] intentArray = {
                        cameraIntent
                };
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooser, 0);
            }
        });

        makeOrSave = (Button) findViewById(R.id.ButtonAddOrSave);
        makeOrSave.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                //AS OF RIGHT NOW ONLY LOADS ONE CHOICE TITLE AND TITLE PAGE NUMBER AND ALSO FRAGMENT DESCRIPTION

                EditText titleET = (EditText) findViewById(R.id.fragmentTitle);
                EditText idPageNumET = (EditText) findViewById(R.id.idPageNum);


                String title = titleET.getText().toString();
                String idPageNum = idPageNumET.getText().toString();
                int idPage = -9;
                try{
                    idPage = Integer.parseInt(idPageNum);
                }catch(NumberFormatException e){
                    Log.d("Msg","There was a number format exception!");
                }


                //create a new fragment object as well as choice
                Choice aNewChoice = new Choice();

                //Fragment aNewFrag = new Fragment(title,Description);
                Fragment aNewFrag = new Fragment();
                aNewFrag.addChoice(aNewChoice);
                aNewFrag.setTitle(title);
                aNewFrag.setId(idPage);
                //aNewFrag.setId(idPage);
                StoryList sl = AdventureCreatorApplication.getStoryList();

                Story story = sl.getStoryById(storyId);

                story.addFragment(aNewFrag);

                // now we have to add it to our story object
                // TODO WE HAVE TO HAVE STORY OBJECT THAT IS ASSOSCIATED WITH THIS FRAGMENT 
                // StoryController.addFragment(ourStoryName , aNewFrag);

                Log.d("fragment", "a new fragment has been created");
                finish();
            }	
        });

        //Might not need... will decide soon, -Lindsay
        //        System.out.println("start listener");
        //        fragmentPartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //
        //            @Override
        //            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //               
        //            }
        //        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_editor, menu);
        return true;
    }

    @Override
    public void update(Fragment model) {
        // TODO reload all fields based on new info from model

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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = (int)info.id;


        CharSequence itemTitle = item.getTitle();
        if (itemTitle.equals("Insert Text")){
            // TODO
            FragmentController.addTextSegment(fragment, "New text", position);

        } else if (itemTitle.equals("Insert Illustration")){
            /*
             * Need this code to return a drawable to add to the fragment
             */

            // From: http://stackoverflow.com/q/16391124/1684866
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
            galleryIntent.setType("image/*");
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            Intent chooser = new Intent(Intent.ACTION_CHOOSER);
            chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
            chooser.putExtra(Intent.EXTRA_TITLE, "Select Illustration From");

            Intent[] intentArray = {
                    cameraIntent
            };
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
            startActivityForResult(chooser, 0);

            Drawable illustration = null;
            FragmentController.addIllustration(fragment, illustration, position);

        } else if (itemTitle.equals("Edit")){
            RelativeLayout curLayout = new RelativeLayout(this);
            
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            System.out.println("1");
            PopupWindow editTextWindow = new PopupWindow(inflater.inflate(R.layout.edit_text_seg_popup, null, true), 50, 50, true);
            System.out.println("2");
            EditText editTextSegView = (EditText) findViewById(R.id.editTextSeg);
            System.out.println("3");
            //editTextSegView.setText(fragment.getTextSegments().get(position));
            System.out.println("4");
            editTextWindow.showAtLocation(curLayout, Gravity.CENTER, 0, 0); 
            System.out.println("5");
            editTextWindow.update();
            
        } else if(itemTitle.equals("Add Choice")){
            //add choice Logic
            //            EditText choice1ET = (EditText) findViewById(R.id.choiceId1);
            //            String choice1 = choice1ET.getText().toString();
            //            aNewChoice.setChoiceText(choice1);

        }else if (itemTitle.equals("Delete")){
            FragmentController.deleteFragmentPart(fragment, position);
        }

        //Make sure the fragment isn't completely empty
        if(fragment.getDisplayOrder().size()==0)
            FragmentController.addEmptyPart(fragment);

        adapter.notifyDataSetChanged();
        return true;
    }
}
