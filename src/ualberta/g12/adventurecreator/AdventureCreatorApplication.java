package ualberta.g12.adventurecreator;

import android.app.Application;

public class AdventureCreatorApplication extends Application {
    // So many singletons!
    private transient static StoryController storyController = null;
    private transient static FragmentController fragmentController = null;
    private transient static StoryList storyList;
    private transient static StoryListController storyListController = null;
    
    public static StoryList getStoryList(){
        
        if(storyList == null){
            storyList = new StoryList();
        }
        return storyList;
    }
    
    public static StoryListController getStoryListController() {
        if (storyListController == null) {
            storyListController = new StoryListController(getStoryList());
        }
        return storyListController;
    }
    
    public static StoryController getStoryController() {
        if (storyController == null) {
            storyController = new StoryController();
        }
        return storyController;
    }
    
    
    
    public static FragmentController getFragmentController() {
        if (fragmentController == null) {
            fragmentController = new FragmentController();
        }
        return fragmentController;
    }
    
    

    // TODO: Implement getters for all of the singletons
}
