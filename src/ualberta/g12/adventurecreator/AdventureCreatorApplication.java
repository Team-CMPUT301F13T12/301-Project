package ualberta.g12.adventurecreator;

import android.app.Application;

public class AdventureCreatorApplication extends Application{
    // So many singletons!
    private transient static StoryController storyController = null;
    private transient static FragmentController fragmentController = null;
    private transient static StoryManager storyManager = null;
    
    public static StoryManager getStoryManager(){
        if(storyManager == null){
            storyManager = new StoryManager();
        }
        return storyManager;
    }
    
    public static StoryController getStoryController(){
        if (storyController == null){
            storyController = new StoryController(); 
        }
        return storyController;
    }
    
    public static FragmentController getFragmentController(){
        if(fragmentController == null){
            fragmentController = new FragmentController();
        }
        return fragmentController;
    }
}
