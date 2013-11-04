package g12.projecttestcases;


import ca.ualberta.cs.livojevi_notes.EntryListActivity;
import ca.ualberta.cs.livojevi_notes.ViewEntryActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class storyListControllerTestCases extends ActivityInstrumentationTestCase2<StoryManager> {
    
    private StoryListController slc;
    private StoryList sl;
    private List<Story> stories;

    public StoryListTestCase(){
        super("ualberta.g12.adventurecreator.StoryListController", StoryListController.class);
    }


    // test adding a story 
    public void testAddStory(){
        
        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        story s = new story("Book","Dan Dude");
        slc.addStory(s);
        int numBooks = slc.getSize();
        
        assert(numBooks != 0);
        
    }
    
      //tests deleting a story
      public void testDeleteStory(){
        
        // add a story to our storyList
        sl = new StoryList();
        slc = new StoryListController(sl);
        story s = new story("Book","Dan Dude");
        slc.addStory(s);
        slc.deleteStory(s);
        int numBooks = slc.getSize();
        
        assert(numBooks == 0);
        
    }
    

    
    
}
