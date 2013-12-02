
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
     * Sets the title of the given story. This will automatically update the id
     * of the story as the id is created from the title and author of the story.<br>
     * If a null value is given, the story's id will be set to an invalid id.
     * 
     * @param s the Story to change the title of
     * @param newTitle the new title of story s
     * 
     */
    public void setTitle(Story s, String newTitle);

    /**
     * Sets the author of the given story. This will automatically update the id
     * of the story as the id is created from the title and author of the story.<br>
     * If a null value is given, the story's id will be set to an invalid id.
     * 
     * @param s the Story to change the author of
     * @param newAuthor the new author of Story s
     * @param s the story to change the author of
     */
    public void setAuthor(Story s, String newAuthor);

    /**
     * Adds a fragment to the story's list of fragments. Behavior is unspecified
     * if either the story of fragment are null.
     * 
     * @param s the story to add a fragment to
     * @param f the list of fragments so a new one can be added
     */
    public void addFragment(Story s, Fragment f);

    /**
     * Sets the given fragment at the given position in the given story.
     * Behavior is unknown if no fragment exists at this position.
     * 
     * @param s is the story where the fragment is contained
     * @param Pos is the Position of the fragment inside the story
     * @param f is the new fragment we wish to set at Pos
     */
    public void setFragmentAtLocation(Story s, int Pos, Fragment f);
}
