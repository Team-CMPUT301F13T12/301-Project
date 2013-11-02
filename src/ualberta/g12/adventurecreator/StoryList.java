
package ualberta.g12.adventurecreator;

import java.util.ArrayList;
import java.util.List;

/** Models the list of stories */
public class StoryList extends LModel{
    private List<Story> stories;
    
    public StoryList(){
        this.stories = new ArrayList<Story>();
        
        // TODO: Implement a way to load and store these stories
        stories.add(new Story("Good Night Moon", "Jay Z"));
        stories.add(new Story("We Have To Go Back", "Jack Shepard"));
    }
    
    public StoryList(Story s){
        this.stories = new ArrayList<Story>();
        this.stories.add(s);
    }
    
    public List<Story> getAllStories(){
        return this.stories;
    }
    
    
}
