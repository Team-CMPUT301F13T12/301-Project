
package ualberta.g12.adventurecreator;

import android.app.Application;

/**
 * Application object for the Application. Used as a static singleton throughout
 * other activities which returns other singletons
 */
public class AdventureCreatorApplication extends Application {
    // So many singletons!
    private transient static StoryController storyController = null;
    private transient static FragmentController fragmentController = null;
    private transient static StoryList storyList;
    private transient static StoryListController storyListController = null;

    /**
     * Returns the StoryList Singleton. If one doesn't exist, we initialize it
     * 
     * @return the StoryList singleton
     */
    public static StoryList getStoryList() {

        if (storyList == null) {
            storyList = new StoryList();
        }
        return storyList;
    }

    /**
     * Returns the StoryListController Singleton. If one doesn't exist, we
     * create one off of our StoryList singleton
     * @return the StoryListController singleton
     */
    public static StoryListController getStoryListController() {
        if (storyListController == null) {
            storyListController = new StoryListController(getStoryList());
        }
        return storyListController;
    }

    /**
     * Returns the StoryController Singleton. If one doesn't exist, we initialize it and then return
     * @return the StoryController Singleton */
    public static StoryController getStoryController() {
        if (storyController == null) {
            storyController = new StoryController();
        }
        return storyController;
    }

    /**
     * Returns the FragmentController Singleton. If one doesn't exist, we initialize it and then return it.
     * @return the FragmentController Singleton*/
    public static FragmentController getFragmentController() {
        if (fragmentController == null) {
            fragmentController = new FragmentController();
        }
        return fragmentController;
    }

    // TODO: Implement getters for all of the singletons
}
