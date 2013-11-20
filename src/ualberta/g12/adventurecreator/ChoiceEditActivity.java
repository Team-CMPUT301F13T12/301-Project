
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity used for choice editing within the selected story. Will allow the
 * user to enter the text for the choice as well as select another fragment
 * within the same story to link the selected fragment to.
 */
public class ChoiceEditActivity extends Activity {
    private Story story;
    private Choice choice;
    private List<String> possibleChoices;
    private int storyPos, fragPos, choicePos, linkedPos = -1;
    private StoryList storyList;
    private Fragment fragment;
    private static final String TAG = "ChoiceEditActivity";
    private EditText myTitleET;
    private StoryListController storyListController = AdventureCreatorApplication
            .getStoryListController();
    private StoryController storyController = AdventureCreatorApplication.getStoryController();
    private FragmentController fragmentController = AdventureCreatorApplication
            .getFragmentController();

    private static final boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_choice);
        // Show the Up button in the action bar.
        setupActionBar();

        Log.d(TAG,"got in");
        
        // storyList = offlineHelper.loadOfflineStories();
        storyList = storyListController.loadStoryOffline(this);
        
        Log.d(TAG,"loaded");

        Intent editChoiceIntent = getIntent();
        storyPos = (Integer) editChoiceIntent.getSerializableExtra("StoryPos");
        fragPos = (Integer) editChoiceIntent.getSerializableExtra("FragmentPos");
        choice = (Choice) editChoiceIntent.getSerializableExtra("Choice");

        Log.d(TAG,"got intents");
        
        // get widget reference
        myTitleET = (EditText) findViewById(R.id.choiceBody);
        // String choiceText =
        // sl.getAllStories().get(storyPos).getFragments().get(fragPos)
        // .getChoices().get(choicePos).getChoiceText();
        // myTitleET.setText(choiceText);

        /*
         * //load save file storyList = offlineHelper.loadOfflineStories();
         * story = storyList.getAllStories().get(storyPos); fragment =
         * story.getFragments().get(fragPos); Choice choice
         * fragment.getChoices().get(choicePos);
         */
        // Bundle extras = i.getExtras();
        // ourStoryId = extras.getInt("OurStoryId");
        // ourFragmentId = extras.getInt("OurFragmentId");
        Button choiceButton = (Button) findViewById(R.id.choiceButton);
        // StoryList sl = AdventureCreatorApplication.getStoryList();

        // ourStory = sl.getAllStories().get(storyPos);
        // Log.d("WHAT IS OUR STORY SIZE?", ourStory.getStoryTitle());
        // ourFragmentList = ourStory.getFragments();
        // fragment = ourFragmentList.get(fragPos);

        story = storyListController.getStoryAtPos(storyPos);

        fragment = storyController.getFragmentAtPos(story, fragPos);
        
        Log.d(TAG,"stroy and fragment");
        
        choiceButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createADialog();

            }
        }

                );

    }

    // http://stackoverflow.com/questions/4473940/android-best-practice-returning-values-from-a-dialog
    // TODO: What does this method do? The name is bad
    private void createADialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Fragment to link {NONE} means end");
        // StoryController sc = new StoryController();
        possibleChoices = getFragmentTitleList(storyController.getFragments(story));
        CharSequence[] chars = possibleChoices.toArray(new CharSequence[possibleChoices.size()]);

        builder.setItems(chars, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                doneSelecting(item);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        if (DEBUG)
            Log.d("Morning", "HI");

    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

        // getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void doneSelecting(int result) {
        if (DEBUG)
            Log.d("the size of possible choices is ", String.format("%d", possibleChoices.size()));
        if (DEBUG)
            Log.d("the number of result is ", String.format("%d", result));
        if (result != (possibleChoices.size() - 1)) {
            linkedPos = result;
        }
    }

    private List<String> getFragmentTitleList(List<Fragment> fragList) {
        List<String> temp = new ArrayList<String>();
        if (DEBUG)
            Log.d("PSADF", String.format("%d", fragList.size()));
        for (int i = 0; i < fragList.size(); i++) {
            temp.add(fragList.get(i).getTitle());
        }
        temp.add("{NONE}");
        return temp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // TODO make own
        getMenuInflater().inflate(R.menu.story_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
         * switch (item.getItemId()) { case android.R.id.home: // This ID
         * represents the Home or Up button. In the case of this // activity,
         * the Up button is shown. Use NavUtils to allow users // to navigate up
         * one level in the application structure. For // more details, see the
         * Navigation pattern on Android Design: // //
         * http://developer.android.com
         * /design/patterns/navigation.html#up-vs-back //
         * NavUtils.navigateUpFromSameTask(this); return true; } return
         * super.onOptionsItemSelected(item);
         */

        // Fragment frag = null;
        // TODO SEARCH FOR FRAGMENT BY FRAGMENT ID?
        // for (int i = 0; i < ourFragmentList.size(); i++){
        // if (ourFragmentList.get(i).getId() == ourFragmentId)
        // frag = ourFragmentList.get(i);
        // }
        // Log.d("WHAT AM BEFORE", String.format("%d",frag.getChoices().size()
        // ));
        // Log.d("WHAT?!", frag.getChoices().get(0).getChoiceText());
        // TODO CHANGE THIS AND REFACTOR ALL CODE IN CLASS
        String Title = myTitleET.getText().toString();
        // Choice newChoice = new Choice();
        //Choice choice = fragment.getChoices().get(choicePos);
        choice.setChoiceText(Title);
        choice.setLinkedToFragmentPos(linkedPos);
        
        Log.d(TAG, "linkedpos, "+linkedPos);
        Fragment linkedFragment;
        if (linkedPos == -1){
            linkedFragment = null;

            Log.d(TAG, "entered, "+linkedFragment);
        } else {
            linkedFragment= storyController.getFragmentAtPos(story, linkedPos);
        }
        Log.d(TAG, "linkedfrag, "+linkedFragment);
        choice.setLinkedToFragment(linkedFragment);
        // TODO needs to be checked;need controller
        // choice.setChoiceText(Title);
        // choice.setLinkedToFragmentPos(linkedPos);
        // fc.addChoice(fragment, newChoice);
        // sl.getAllStories().get(storyPos).getFragments().set(fragPos,
        // fragment);
        // int i = fragment.getChoices().size();
        // if(DEBUG) Log.d("DID IT GROW?", String.format("%d", i));
        // sl.getAllStories().get(storyPos).getFragments().get(fragPos).getChoices()
        // .set(choicePos, choice);
        // ourStory = storyListController.getAllStories().get(storyPos);
        // StoryController sc = new StoryController();
        // sc.setFragmentAtLocation(ourStory, fragPos, fragment);
        // int c =
        // sl.getAllStories().get(storyPos).getFragments().get(fragPos).getChoices()
        // .get(choicePos).getLinkedToFragmentPos();
        // if(DEBUG) Log.d("EDITCHOICE", "The choices linked to is " + c);
        storyListController.saveOfflineStories(this, storyList);
        // offlineHelper.saveOfflineStories(sl);
        finish();
        return true;
    }

}
