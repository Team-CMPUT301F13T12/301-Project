
package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Models the list of stories. This object is a collection of stories that we wish to 
 * edit/view. Can be though of as a library. 
 * Methods in this class can be used to obtain the different stories in a collection.
 * 
 * We can obtain stories from this collection using the various getter methods ,
 * and also set and add different stories with the setter methods.
 *
 */
@SuppressWarnings("rawtypes")
public class StoryList extends LModel implements Serializable {
    /**
     * For Lint
     */
    private static final long serialVersionUID = 1L;
    private List<Story> stories;

    /**
     * Constructor for the StoryList class. This constructor creates a new empty list of stories.
     * Should only be used if we want to add stories to it later or creating a new collection.
     */
    public StoryList() {
        this.stories = new ArrayList<Story>();
    }

    /**
     * Gets all stories inside from the storyList. 
     * This is used when ALL stories needs to be obtained
     * For Example it can be called to obtain all the stories to be shown in a list for some views.
     * 
     * @return the list of stories
     */
    public List<Story> getAllStories() {
        return this.stories;
    }

    /**
     * Sets all stories in story list. This method should be used to fill our storyList if it was empty before,
     * or if we updated it and adding one at a time would be too troublesome. 
     * 
     */
    public void setAllStories(List<Story> stories) {
        this.stories = stories;
        notifyViews();
    }

    /**
     * adds a story to the story listview/application.
     * 
     * By adding a new story we also have to notify our views that a story has been added so that
     * they can update their representation of the story list. For example a listview maybe updated
     * to reflect that a story has been added to our storylist in the MainActivity
     * 
     * @param s story that has been added
     */
    public void addStory(Story s) {
        stories.add(s);
        notifyViews();
    }

    /**
     * deletes a story from the listview/application
     * 
     * Deleting a story should also notify the different views that depend on the storyList. 
     * Listviews maybe need to be refreshed or updated and the notify the views the object has changed. 
     * @param s story to be deleted
     */
    public void deleteStory(Story s) {
        stories.remove(s);
        notifyViews();
    }

    /**
     * finds if story list contains a story object. This will search the each storyList object and compare
     * it to the one in the input argument.
     * 
     * If no story matches the parameter then a NULL object is returned.
     * 
     * @param s storylist of stories
     * @return number of stoies within the story list
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
     * finds and returns a story based on title. 
     * 
     * An Example of this call would be if a story called "Starry Nights" wants to be found
     * we would called storyList.getStory("StarryNights") to find the story called "Starry Nights"
     * 
     * If no story mathces the title then a NULL object is returned
     * 
     * @param title tile of the story
     * @return story based on title
     */
    public Story getStory(String title) {
        // Shouldn't this method be called getSToryByTitle then?
        // Chris
        Story temp = null;
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i).getTitle().equals(title)) {
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

    /**
     * Returns the story at the specified position in the list of stories.
     */
    public Story getStoryAtPos(int pos) {
        return stories.get(pos);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.stories);
    }

    @SuppressWarnings("unchecked")
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.stories = (List<Story>) in.readObject();
    }

}
