
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.OfflineIOHelper;
import ualberta.g12.adventurecreator.data.Story;
import ualberta.g12.adventurecreator.data.StoryList;

import java.util.List;

/**
 * A controller class that is used to modify the StoryList data singleton. All
 * changes to the StoryList singleton should be done through this class. This
 * class will automatically save changes to the StoryList singleton using the
 * OfflineHelper Singleton.
 */
public class StoryListController {
    private StoryList sl = null;

    private OfflineIOHelper offlineIOHelper;

    /**
     * Constructs a new StoryListController object set to the given StoryList
     * and OfflineIoHelper Singletons.
     * 
     * @param sc The {@link StoryList} that this StoryListController will be
     *            modifying
     * @param oih The {@link OfflineIOHelper} that we'll be using to save the
     *            changes to the StoryList
     */
    public StoryListController(StoryList sc, OfflineIOHelper oih) {
        this.sl = sc;
        this.offlineIOHelper = oih;
    }

    /**
     * Adds the given story to the StoryList. If an identical story already
     * exists, (same title and author), the original story will not be modified.
     * 
     * @param s story to be added to the list
     */
    public void addStory(Story s) {
        sl.addStory(s);
    }

    /**
     * Removes the story given from the list. If the story doesn't exist then
     * nothing happens.
     * 
     * @param s story to be deleted
     */
    public void deleteStory(Story s) {
        sl.deleteStory(s);
    }

    /**
     * Replaces the story currently at position pos with the given story. If the
     * position given is out of the story list bounds, the given story isn't
     * added to the StoryList
     * 
     * @param s the story to replace with
     * @param pos the position to replace the story at
     */
    public void setStory(Story s, int pos) {
        List<Story> stories = sl.getAllStories();
        if (pos < 0 || pos >= stories.size()) {
            // Position was out of bounds
            return;
        }
        stories.set(pos, s);
        sl.setAllStories(stories);
    }

    /**
     * Called whenever we want to save all of the stories in the StoryList to
     * disk using the OfflineIOHelper. If the storylist is null, the offline
     * Helper does not try to save any stories.
     * 
     * @param storyList is the storyList that we want to save
     */
    public void saveOfflineStories(StoryList storyList) {
        this.offlineIOHelper.saveOfflineStories(storyList);

    }

    /**
     * Creates a blank story by calling the no argument constructor of
     * {@link Story} and adds it to the StoryList.
     */
    public void createBlankStory() {
        Story story = new Story();
        sl.addStory(story);
    }

}
