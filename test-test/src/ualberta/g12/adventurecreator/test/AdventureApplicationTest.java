
package ualberta.g12.adventurecreator.test;

import android.test.AndroidTestCase;

import org.junit.Test;

import ualberta.g12.adventurecreator.AdventureCreatorApplication;
import ualberta.g12.adventurecreator.FragmentController;
import ualberta.g12.adventurecreator.StoryController;
import ualberta.g12.adventurecreator.StoryList;
import ualberta.g12.adventurecreator.StoryListController;

public class AdventureApplicationTest extends AndroidTestCase
{
    private AdventureCreatorApplication application;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Create it even though we don't use this
        this.application = new AdventureCreatorApplication();
    }

    @Test
    public void testSingletonsNotNull() {
        assertNotNull(AdventureCreatorApplication.getStoryList());
        assertNotNull(AdventureCreatorApplication.getStoryController());
        assertNotNull(AdventureCreatorApplication.getStoryController());
        assertNotNull(AdventureCreatorApplication.getFragmentController());
    }

    @Test
    public void testStoryList() {
        StoryList sl = AdventureCreatorApplication.getStoryList();
        assertNotNull(sl);
        assertSame(sl, AdventureCreatorApplication.getStoryList());
    }

    @Test
    public void testStoryListController() {
        StoryListController slc = AdventureCreatorApplication.getStoryListController();
        assertNotNull(slc);
        assertSame(slc, AdventureCreatorApplication.getStoryListController());
    }

    @Test
    public void testStoryController(){
        StoryController sc = AdventureCreatorApplication.getStoryController();
        assertNotNull(sc);
        assertSame(sc, AdventureCreatorApplication.getStoryController());
    }
    
    @Test
    public void testFragmentController(){
        FragmentController fc = AdventureCreatorApplication.getFragmentController();
        assertNotNull(fc);
        assertSame(fc, AdventureCreatorApplication.getFragmentController());
    }
}
