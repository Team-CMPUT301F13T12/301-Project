
package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

/**
 * Activity when an author creates a new story. Will create a new story entry
 * and save the title and author strings. Will take the user to corresponding
 * activities upon button clicks.
 */
public class CreateStoryActivity extends Activity {
    private Story story;
    private StoryList storyList;
    private StoryListController storyListController;
    private StoryController storyController;
    private int storyPos;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);

        createButton = (Button) findViewById(R.id.editTextSave);

        storyListController = AdventureCreator.getStoryListController();
        storyController = AdventureCreator.getStoryController();

        // obtain the intent
        Intent editActIntent = getIntent();
        storyPos = (Integer) editActIntent.getSerializableExtra("StoryPos");

        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        storyList = AdventureCreator.getStoryList();
        story = storyList.getAllStories().get(storyPos);
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
                storyController.setTitle(story, title.getText().toString());
                storyController.setAuthor(story, author.getText().toString());

                // save the new story
                saveStory();

                finish();
            }
        });
    }

    private void saveStory() {
        storyListController.setStory(story, storyPos);
        storyListController.saveOfflineStories(storyList);
    }
}
