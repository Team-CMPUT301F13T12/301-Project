package g12.projecttestcases;
/* Covers test cases for use cases:
 * 4, 5
 * 
 */


import ca.ualberta.cs.livojevi_notes.EntryListActivity;
import ca.ualberta.cs.livojevi_notes.ViewEntryActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class StoryManagerTestCases extends ActivityInstrumentationTestCase2<StoryManager> {
    
    private StoryManger sm;
    private List<Story> stories;

    public StoryManagerTestCase(){
        super("ualberta.g12.adventurecreator.StoryManager", StoryManager.class);
    }

    @Override
    public void setUp(){
        super.setUp();
        sm = StoryManager.getInstance();
    }
    
    // Use case 4, test 1/4
    public void testSearchForStoryByTitle(){
        
        // Create a story we can search for
        sm.publishStory(new Story("Blank story", "Blank Author")); 
        stories = sm.findStoryByTitle("Blank");
        
        assert(!stories.isEmpty());
    }
    
    // Use case 4, test 2/4
    public void testSearchForStoryByAuthor(){
        sm.publishStory(new Story("Anonymous story", "Anonymous"));
        stories = sm.findStoryByAuthor("Anonymous");
        
        assert(!stories.isEmpty());
    }
    
    // Use case 4, test 3/4
    public void testSearchForInvalidStory(){
        stories = sm.findStoryByAuthor(null);
        
        assert(stories.isEmpty());
    }
    
    // Use case 4, test 4/4
    public void testSearchForMultipleStories(){
        sm.publishStory(new Story("a1", "a1"));
        sm.publishStory(new Story("b1", "b1"));
        sm.publishStory(new Story("a2", "a2"));
        
        stories = sm.findStoryByTitle("a");
        
        assert(stories.size() > 1);
        
        stories = sm.findStoryByAuthor("1");
        
        assert(stories.size() > 1);
    }
    
    // Use case 5, test 1/5
    public void testEditStory(){
        sm.publishStory(new Story("this is", "no fun"));
        stories = sm.findStoryByTitle("this");
        
        assert(!stories.isEmpty());
        
        startEditStory(stories.get(0));
    }
}
