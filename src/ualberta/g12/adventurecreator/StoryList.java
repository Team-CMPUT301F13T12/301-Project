
package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Models the list of stories */
public class StoryList extends LModel implements Serializable{
    private List<Story> stories;

    public StoryList() {
        this.stories = new ArrayList<Story>();

//        // TODO: Implement a way to load and store these stories
        stories.add(new Story("Good Night Moon", "Jay Z"));
        stories.add(new Story("We Have To Go Back", "Jack Shepard"));
    }

    public StoryList(Story s) {
        this.stories = new ArrayList<Story>();
        this.stories.add(s);
    }

    /**
     * gets all stories in story list
     * 
     * @return
     */
    public List<Story> getAllStories() {
        return this.stories;
    }
    
    /**
     * sets all stories in story list
     * 
     * @return
     */
    public void setAllStories(List<Story> stories) {
        this.stories = stories;
    }

    /**
     * adds a story
     * 
     * @param s
     */
    public void addStory(Story s) {
        stories.add(s);
        notifyViews();
    }

    /**
     * deletes a story
     * 
     * @param s
     */
    public void deleteStory(Story s) {
        stories.remove(s);
        notifyViews();
    }

    /**
     * finds if story list contains a story object
     * 
     * @param s
     * @return
     */
    public Story getStory(Story s) {
        Story temp = null;
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i) == s) {
                temp = stories.get(i);
            }
        }
        return temp;
    }

    /**
     * finds and returns a story based on title
     * 
     * @param title
     * @return
     */
    public Story getStory(String title) {
        // Shouldn't this method be called getSToryByTitle then?
        // Chris
        Story temp = null;
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i).getStoryTitle() == title) {
                temp = stories.get(i);
            }
        }
        return temp;
    }

    /**
     * Finds and returns the story based on the stories id <br>
     * If two stories have the same id - this should be impossible, return the
     * first story that we find
     * 
     * @param id of story to return
     * @return the story or null
     */
    public Story getStoryById(int id) {
        for (Story s : stories) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        out.writeObject(this.stories);
    }
    
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        this.stories = (List<Story>) in.readObject();
    }

}
