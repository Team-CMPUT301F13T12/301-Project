
package ualberta.g12.adventurecreator;

import java.util.ArrayList;
import java.util.List;

/** Models the list of stories */
public class StoryList extends LModel{
    private List<Story> stories;
    
    public StoryList(){
        this.stories = new ArrayList<Story>();
    }
    
    public StoryList(Story s){
        this.stories = new ArrayList<Story>();
        this.stories.add(s);
    }
    
    public List<Story> getAllStories(){
        return this.stories;
    }
    
    
}
