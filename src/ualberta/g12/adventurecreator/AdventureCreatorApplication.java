package ualberta.g12.adventurecreator;

import android.app.Application;

public class AdventureCreatorApplication extends Application{
    // So many singletons!
    private transient static StoryController storyController = null;
    private transient static FragmentController fragmentController = null;
    
    public static StoryController getStoryController(){
        if (storyController == null){
            storyController = new StoryController(); 
        }
        return storyController;
    }

    // TODO: Implement getters for all of the singletons
}
