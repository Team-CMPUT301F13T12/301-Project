
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
     * @param f the fragment to add to the story
     */
    public void addFragment(Story s, Fragment f);

    /**
     * Deletes the given fragment from the given story if it exists.
     * 
     * @return true if fragment is deleted successfully else false
     * @param f the id of the fragment to delete
     */
    public boolean deleteFragment(Story s, Fragment f);
}
