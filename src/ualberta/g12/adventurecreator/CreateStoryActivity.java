<<<<<<< HEAD
=======

>>>>>>> vincet1234
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD

public class CreateStoryActivity extends Activity {
	
	Button createButton;
=======
import android.widget.EditText;

/**
 * Activity when an author creates a new story. Will create a new story entry
 * and save the title and author strings. Will take the user to corresponding
 * activities upon button clicks.
 */
public class CreateStoryActivity extends Activity {
    private Story story;
    private StoryList storyList;
    private OfflineIOHelper offlineHelper = new OfflineIOHelper(CreateStoryActivity.this);
    private int storyPos;
    private StoryListController storyListController = AdventureCreatorApplication
            .getStoryListController();
    private StoryController storyController = AdventureCreatorApplication.getStoryController();
    private Button createButton;
>>>>>>> vincet1234

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);
<<<<<<< HEAD
        
        createButton = (Button) findViewById(R.id.button1);
        createButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplication(), StoryEditActivity.class));
			}
		});
=======

        createButton = (Button) findViewById(R.id.editTextSave);

        // obtain the intent
        Intent editActIntent = getIntent();
        storyPos = (Integer) editActIntent.getSerializableExtra("StoryPos");

        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // storyList = offlineHelper.loadOfflineStories();
        // story = storyList.getAllStories().get(storyPos);
        storyList = storyListController.loadStoryOffline(this);
        story = storyListController.getAllStories().get(storyPos);
>>>>>>> vincet1234
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

<<<<<<< HEAD

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
    
    
=======
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {

        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get the editTexts!
                EditText title = (EditText) findViewById(R.id.editStoryTitle);
                EditText author = (EditText) findViewById(R.id.editStoryAuthor);

                // create a new story (might want to use a controller here
                // instead!)
                // story.setStoryTitle(title.getText().toString());
                // story.setAuthor(author.getText().toString());

                storyController.setTitle(story, title.getText().toString());
                storyController.setAuthor(story, author.getText().toString());

                // save the new story
                saveStory();

                finish();
            }
        });
    }

    private void saveStory() {

        // storyList.getAllStories().set(storyPos, story);
        // offlineHelper.saveOfflineStories(storyList);

        storyListController.saveOfflineStories(this, storyList);
    }
>>>>>>> vincet1234
}
