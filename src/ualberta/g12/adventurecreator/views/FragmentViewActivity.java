package ualberta.g12.adventurecreator.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import ualberta.g12.adventurecreator.R;
import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;

/**
 * Activity for viewing a fragment.  User can follow choices and will
 * be taken to the next fragment.
 */
public class FragmentViewActivity extends Activity implements FView<Fragment> {

    private TextView fragmentTitleTextView;
    private ListView fragmentPartListView;
    private FragmentPartAdapter adapter;
    private FragmentController fragmentController;
    private StoryController storyController;
    //private static final String TAG = "FragmentViewActivity";
    private Story story;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_viewer);

        fragmentController = AdventureCreator.getFragmentController();
        storyController = AdventureCreator.getStoryController();
        
        // obtain the intent
        Intent viewFragIntent = getIntent();
        story = (Story) viewFragIntent.getSerializableExtra("Story");
        
        // get widget references
        fragmentPartListView = (ListView) findViewById(R.id.fragmentViewPartList);
        fragmentTitleTextView = (TextView) findViewById(R.id.fragmentTitleText);

        setListClickListener();
        
        // set fragment to first fragment in story
        int fragPos = story.getStartFragPos();
        fragment = AdventureCreator.getStoryController().getFragmentAtPos(story,
                fragPos);
    }

    @Override
    protected void onStart() {
        super.onStart();    
        update();
   }

    @Override
    public void update(Fragment model) {
        // TODO reload all fields based on new info from model
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
    }

    private void setListClickListener() {
        fragmentPartListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int goToFragPos = fragmentController.getLinkedToFragmentPosOfChoice(fragment, position);
                Fragment goToFrag = null;
                if(goToFragPos != -1)
                    goToFrag = storyController.getFragmentAtPos(story, goToFragPos);
                
                if (goToFrag != null){
                    fragment = goToFrag;
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
}
