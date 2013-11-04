package g12.projecttestcases;



import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

public class storyFragmentControllerTestCases extends ActivityInstrumentationTestCase2<StoryFragment> {
    
    private StoryFragmentController sfc;
    private StoryFragment sf;
    

    public StoryFragmentControllerTestCase(){
        super("ualberta.g12.adventurecreator.StoryFragmentController", StoryFragmentController.class);
    }


    // test editing Body Text
    public void testEditText(){
        sf = new sf("Lunch", "You eat lunch mmmm");
        sfc.editBodyText(sf , "who eats lunch");
        
        assert(sf.bodyText == "who eats lunch");
        
    }
    
      //tests editing title
      public void testDeleteStory(){
        
        sf = new sf("Lunch", "You eat lunch mmmm");
        sfc.editTitle(sf , "Dinner");
        
        assert(sf.bodyText == "Dinner");
        
    }
    
          //tests add choice
      public void testAddChiuce(){
        
        sf = new sf("Lunch", "You eat lunch mmmm");
        sfc.editTitle(sf , "Dinner");
        Choice c = new Choice("To the kitchen!", 39);
        sfc.editAddChoice(sf, c);
        
        assert(sfc.choices.contains(c));
        
    }
          //tests deleting Choice
      public void testDeleteStory(){
        
        sf = new sf("Lunch", "You eat lunch mmmm");
        sfc.editTitle(sf , "Dinner");
        Choice c = new Choice("To the kitchen!", 39);
        sfc.editAddChoice(c);
        
        assert(sfc.choices.contains(c));
        
        sfc.deleteChoice(sf, c);
        
        assert(!sfc.choice.contains(c));
        
    }

  
    
    
}
