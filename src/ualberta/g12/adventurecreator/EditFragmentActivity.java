
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


// Right now we are making Fragments here and also choice
// What i was thinking was maybe just make the general fragment here
// and have another screen to add choices then we could have list a listview type thing so can have a lot of choices
// and long clicking each one would let you edit/delete them 
// TODO discuss with the team
//
public class EditFragmentActivity extends Activity implements FView<Fragment> {

    private Button addImage;
    private Button makeOrSave;
    private Fragment fragment;
    private FragmentController fragmentController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_editor);

        // obtain the intent
        Intent editActIntent = getIntent();
        Bundle bundledExtras = editActIntent.getExtras();
        String editType = bundledExtras.getString("EditType");
        
        if (editType.equals("Edit") == false){
        	// TODO: Load our fragment from the story model using the id given to us as an extra ( if we are editing an existing fragment)
        	int id  = bundledExtras.getInt("FragmentId");
        	// load title , id/page number , fragment description, choices
        	
        }
        // else then we are adding a new fragment dont need to load stuff

        
        // TODO: Set the fragmentController to our Fragment
        
        // TODO: Load our fragment into view
        
        // TODO: Set up on clickers
        setUpOnClickListeners();

    }

    private void setUpOnClickListeners() {
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
				EditText DescriptionET = (EditText) findViewById(R.id.fragmentBody);
				EditText choice1ET = (EditText) findViewById(R.id.choiceId1);
				
				String title = titleET.getText().toString();
				String idPageNum = idPageNumET.getText().toString();
				String Description = DescriptionET.getText().toString();
				String choice1 = choice1ET.getText().toString();
				//create a new fragment object as well as choice
				Choice aNewChoice = new Choice();
				aNewChoice.setChoiceText(choice1);
				
				//Fragment aNewFrag = new Fragment(title,Description);
				Fragment aNewFrag = new Fragment();
				aNewFrag.addChoice(aNewChoice);
				
				// now we have to add it to our story object
				// TODO WE HAVE TO HAVE STORY OBJECT THAT IS ASSOSCIATED WITH THIS FRAGMENT 
				// StoryController.addFragment(ourStoryName , aNewFrag);
				
				Log.d("fragment", "a new fragment has been created");
				
				
				
				
            }	
        	
        	
        	
        });
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

}
