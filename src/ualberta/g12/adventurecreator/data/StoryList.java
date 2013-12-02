
package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Models the list of stories. This object is a collection of Story objects that
 * we wish to edit/view. Can be though of as a library. Methods in this class
 * can be used to obtain the different stories in a collection. We can obtain
 * stories from this collection using the various getter methods , and also set
 * and add different stories with the setter methods. object of this class is
 * instantiated all fields are initialized to non-null values.
 * 
 * @see Story
 */
@SuppressWarnings("rawtypes")
public class StoryList extends LModel implements Serializable {
    /**
     * For Lint
     */
    private static final long serialVersionUID = 1L;
    private List<Story> stories;

    /**
     * Constructor for the StoryList class. This constructor creates a new empty
     * list of stories. Should only be used if we want to add stories to it
     * later or creating a new collection.
     * 
     * @see Story
     */
    public StoryList() {
        this.stories = new ArrayList<Story>();
    }

    /**
     * Gets all stories inside from the storyList. This is used when ALL stories
     * needs to be obtained For Example it can be called to obtain all the
     * stories to be shown in a list for some views.
     * 
     * @return stories the list of stories
     * @see Story
     */
    public List<Story> getAllStories() {
        return this.stories;
    }

    /**
     * Sets all stories in story list. This method should be used to fill our
     * storyList if it was empty before, or if we updated it and adding one at a
     * time would be too troublesome.
     * 
     * @param stories the list of stories to be set
     * @see Story
     */
    public void setAllStories(List<Story> stories) {
        this.stories = stories;
        notifyViews();
    }

    /**
     * adds a story to the story listview/application. By adding a new story we
     * also have to notify our views that a story has been added so that they
     * can update their representation of the story list. For example a listview
     * maybe updated to reflect that a story has been added to our storylist in
     * the MainActivity
     * 
     * @param s story that has been added
     * @see Story
     */
    public void addStory(Story s) {
        stories.add(s);
        notifyViews();
    }

    /**
     * Returns the story at the specified position in the list of stories.
     * 
     * @param pos the index of the Story in the list of stories that is to be
     *            retrieved
     * @see Story
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
