
package ualberta.g12.adventurecreator.test;

import android.test.AndroidTestCase;

import ualberta.g12.adventurecreator.controllers.FragmentController;
import ualberta.g12.adventurecreator.controllers.StoryController;
import ualberta.g12.adventurecreator.controllers.StoryListController;
import ualberta.g12.adventurecreator.data.AdventureCreator;
import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.StoryList;
import ualberta.g12.adventurecreator.online.OnlineHelper;

/**
 * Tests the AdventureCreator the custom application object we wrote for the application
 * The application is used to as a static singleton throughout
 * in which it can return other singletons (mostly controllers and helpers)
 * 
 * Adventure Application contains:
 * StoryController,FragmentController,StoryList,StoryListController,OfflineIOHelper,OnlineHelper
 *
 */
public class AdventureApplicationTest extends AndroidTestCase
{
    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    /**
     * We used this test to ensure that all our singleTons are not null, as they are 
     * supposed to be created if they do not exist on the first get[Singleton] method call
     * [Singleton] can be: StoryController,FragmentController,
     *  StoryList,StoryListController,OfflineIOHelper,OnlineHelper
     */
    public void testSingletonsNotNull() {
        assertNotNull(AdventureCreator.getStoryList());
        assertNotNull(AdventureCreator.getStoryController());
        assertNotNull(AdventureCreator.getStoryListController());
        assertNotNull(AdventureCreator.getFragmentController());
        assertNotNull(AdventureCreator.getOfflineIOHelper());
        assertNotNull(AdventureCreator.getOnlineHelper());
    }
    
    /**
     * Tests to see that the storyList singleton is initiated correctly, and 
     * a new storyList is not created every time we call the get function
     */
    public void testStoryList() {
        StoryList sl = AdventureCreator.getStoryList();
        assertNotNull(sl);
        assertSame(sl, AdventureCreator.getStoryList());
    }

    
    /**
     * Tests to see that the StoryListController singleton is initiated correctly, and 
     * a new StoryListController is not created every time we call the get function
     */
    public void testStoryListController() {
        StoryListController slc = AdventureCreator.getStoryListController();
        assertNotNull(slc);
        assertSame(slc, AdventureCreator.getStoryListController());
    }

    /**
     * Tests to see that the StoryController singleton is initiated correctly, and 
     * a new StoryController is not created every time we call the get function
     */
    public void testStoryController() {
        StoryController sc = AdventureCreator.getStoryController();
        assertNotNull(sc);
        assertSame(sc, AdventureCreator.getStoryController());
    }
    
    /**
     * Tests to see that the FragmentController singleton is initiated correctly, and 
     * a new FragmentController is not created every time we call the get function
     */
    
    public void testFragmentController() {
        FragmentController fc = AdventureCreator.getFragmentController();
        assertNotNull(fc);
        assertSame(fc, AdventureCreator.getFragmentController());
    }
    
    /**
     * Tests to see that the FragmentController singleton is initiated correctly, and 
     * a new FragmentController is not created every time we call the get function
     */
    
    public void testOfflineHelper() {
    	OfflineIOHelper oh = AdventureCreator.getOfflineIOHelper();
        assertNotNull(oh);
        assertSame(oh, AdventureCreator.getOfflineIOHelper());
    }
    
    /**
     * Tests to see that the FragmentController singleton is initiated correctly, and 
     * a new FragmentController is not created every time we call the get function
     */
    
    public void testOnlineHelper() {
    	OnlineHelper oh = AdventureCreator.getOnlineHelper();
        assertNotNull(oh);
        assertSame(oh, AdventureCreator.getOnlineHelper());
    }
    
    
}
