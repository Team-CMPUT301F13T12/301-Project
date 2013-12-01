
package ualberta.g12.adventurecreator.test;

import android.test.AndroidTestCase;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.StoryList;

public class AdventureApplicationTest extends AndroidTestCase
{
    private AdventureCreator application;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Create it even though we don't use this
        this.application = new AdventureCreator();
    }

    public void testSingletonsNotNull() {
        assertNotNull(AdventureCreator.getStoryList());
        assertNotNull(AdventureCreator.getStoryController());
        assertNotNull(AdventureCreator.getStoryController());
        assertNotNull(AdventureCreator.getFragmentController());
    }

    public void testStoryList() {
        StoryList sl = AdventureCreator.getStoryList();
        assertNotNull(sl);
        assertSame(sl, AdventureCreator.getStoryList());
    }

    public void testStoryListController() {
        StoryListController slc = AdventureCreator.getStoryListController();
        assertNotNull(slc);
        assertSame(slc, AdventureCreator.getStoryListController());
    }

    public void testStoryController(){
        StoryController sc = AdventureCreator.getStoryController();
        assertNotNull(sc);
        assertSame(sc, AdventureCreator.getStoryController());
    }
    
    public void testFragmentController(){
        FragmentController fc = AdventureCreator.getFragmentController();
        assertNotNull(fc);
        assertSame(fc, AdventureCreator.getFragmentController());
    }
}
