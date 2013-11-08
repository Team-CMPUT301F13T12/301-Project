
package ualberta.g12.adventurecreator.test;

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import ualberta.g12.adventurecreator.Choice;
import ualberta.g12.adventurecreator.EditFragmentActivity;
import ualberta.g12.adventurecreator.Fragment;
import ualberta.g12.adventurecreator.FragmentController;

public class StoryFragmentControllerTestCases extends TestCase {

  

	private FragmentController fc;
    private Fragment sf;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fc = new FragmentController();
    }

    /**
     * Tests editing the title of a story fragment with a story Fragment Controller
     */
    public void testEditTitle() {
        sf = new Fragment();
        fc.editTitle(sf, "LUNCH");

         assert(sf.getTitle().equals("LUNCH"));
    }

    /**
     * Tests adding Text Segments to a  story fragment with a story Fragment Controller
     */
    public void testAddTextSegment() {

    	sf = new Fragment();
    	fc.addTextSegment(sf,"Text",0);
    	List<String> myList = sf.getTextSegments();
    	assertTrue(myList.get(0).equals("Text"));

    }


    /**
     * Tests deleting an existing choice of a  story fragment with a story Fragment Controller
     */
    public void testDeleteStory() {

    	sf = new Fragment();
    	fc.addTextSegment(sf,"Text",0);
    	List<String> myList = sf.getTextSegments();
    	assertTrue(myList.get(0).equals("Text"));
    	assertTrue(sf.getTextSegments().size() == 1);
    	fc.deleteFragmentPart(sf, 0);
        assertTrue(sf.getTextSegments().size() == 0);

    }
    
    /**
     * Tests inserting an "Illustration"  to a  story fragment with a story Fragment Controller
     */
    public void testInsertIllustration(){
    	sf = new Fragment();
    	fc.addIllustration(sf, "illustration1", 0);
    	List<String>myList = sf.getIllustrations();
    	assertTrue(myList.get(0).equals("illustration1"));
    	assertTrue(sf.getIllustrations().size() == 1);
    }
    
    
    /**
     * Tests deleting an "Illustration"  of a  story fragment with a story Fragment Controller
     */
    public void testDeleteIllustration(){
    	sf = new Fragment();
    	fc.addIllustration(sf, "illustration1", 0);
    	List<String>myList = sf.getIllustrations();
    	assertTrue(myList.get(0).equals("illustration1"));
    	assertTrue(sf.getIllustrations().size() == 1);
    	fc.deleteFragmentPart(sf, 0);
    	assertTrue(sf.getIllustrations().size() == 0);
    }
    
    
    /**
     * Tests adding a choice to a Story Fragment with a story Controller fragment 
     */
    public void testAddChoice(){
    	sf = new Fragment();
    	Choice c = new Choice();
    	c.setChoiceText("Test");
    	fc.addChoice(sf, c);
    	assertTrue(sf.getChoices().get(0).getChoiceText().equals("Test"));
    }
    
    public void testDeleteChoice(){
       	sf = new Fragment();
    	Choice c = new Choice();
    	c.setChoiceText("Test");
    	fc.addChoice(sf, c);
    	assertTrue(sf.getChoices().get(0).getChoiceText().equals("Test"));
    	fc.deleteChoice(sf,0);
    	assertTrue(sf.getChoices().size() == 0);
    	
    }
    
    public void testAddEmptyPart(){
    	sf = new Fragment();
    	fc.addEmptyPart(sf);
    	assertTrue(sf.getDisplayOrder().get(0).equals("e"));
    	
    }
    
    public void testRemoveEmptyPart(){
    	sf = new Fragment();
    	fc.addEmptyPart(sf);
    	assertTrue(sf.getDisplayOrder().get(0).equals("e"));
    	fc.removeEmptyPart(sf);
    	assertTrue(sf.getDisplayOrder().size() == 0);
    	
    }
    
    public void testDeleteFragmentPart(){
    	sf = new Fragment();
    	fc.addTextSegment(sf,"Text",0);
    	List<String> myList = sf.getTextSegments();
    	assertTrue(myList.get(0).equals("Text"));
    	fc.deleteFragmentPart(sf, 0);
    	assertTrue(sf.getDisplayOrder().size() == 0);
    }

}
