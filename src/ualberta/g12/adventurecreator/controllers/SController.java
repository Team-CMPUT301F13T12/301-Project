
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.SModel;
import ualberta.g12.adventurecreator.data.Story;

/**
 * Controller class for modifying a {@link SModel} Object. Any class that
 * modifies an modifies an extension of {@link SModel} should implement this
 * class.<br>
 * Provides method stubs for modifying a {@link SModel} object.
 */
public interface SController {

    /**
     * Adds a fragment to the a story
     * 
     * @param s the Story to add the Fragment to
     * @param f the fragment to add to the story
     */
    public void addFragment(Story s, Fragment f);

    /**
     * Deletes the given fragment from the given story if it exists.
     * 
     * @param s the Story to add the Fragment to
     * @param f the id of the fragment to delete
     * @return boolean true if fragment is deleted successfully else false
     */
    public boolean deleteFragment(Story s, Fragment f);
}
