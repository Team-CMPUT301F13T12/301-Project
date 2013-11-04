
package ualberta.g12.adventurecreator;

import android.app.Application;

public class AdventureCreatorApplication extends Application {
    // So many singletons!
    private transient static StoryController storyController = null;
    private transient static FragmentController fragmentController = null;
    private transient static StoryList storyList = null;
    private transient static StoryListController storyListController = null;

    public static StoryList getStoryList(){
        if(storyList == null){
            storyList = new StoryList();
        }
        return storyList;
    }
    
    public static StoryController getStoryController() {
        if (storyController == null) {
            storyController = new StoryController();
        }
        return storyController;
    }
    
    public static StoryListController getStoryListController() {
        if (storyController == null) {
            storyListController = new StoryListController(storyList);
        }
        return storyListController;
    }
    
    

    // TODO: Implement getters for all of the singletons
}
