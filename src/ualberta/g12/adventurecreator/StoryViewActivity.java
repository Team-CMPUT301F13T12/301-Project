
package ualberta.g12.adventurecreator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class StoryViewActivity extends Activity {

    public static final String INTENT_STORY_ID = "storyId";
    
    private StoryList storyList;
    private Story story;
    private int storyPos, fragPos;
    private Fragment fragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
    }
    
    /*
     * To view a fragment please use this intent:
     * (storyList, Story, storyPos, 
     */
    

//    fragPos = story.getStartFragPos();
//    Fragment frag = story.getFragments().get(fragPos)));
//
//    Intent intent = new Intent(this, EditFragmentActivity.class);
//    intent.putExtra("Mode", "View");
//    intent.putExtra("StoryList", storyList);
//    intent.putExtra("StoryPos",storyPos);
//    intent.putExtra("Story", story);
//    intent.putExtra("FragmentPos", fragPos);
//    intent.putExtra("Fragment", fragment);
//    startActivity(intent);

}
