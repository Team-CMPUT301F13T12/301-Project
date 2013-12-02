
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.Story;

import java.util.List;

/**
 * Controller for the stories that deal with the information regarding stories
 * in the application. Controls the ability to add or edit the title and author
 * of the stories, as well as add fragments to a selected story.
 */
public class StoryController implements SController {

    /**
     * Sets the title of the given story. This will automatically update the id
     * of the story as the id is created from the title and author of the story.<br>
     * If a null value is given, the story's id will be set to an invalid id.
     * 
     * @param newTitle the new title of story s
     * @param s the story to change the title of
     */
    public void setTitle(Story s, String newTitle) {
        s.setTitle(newTitle);
    }

    /**
     * Sets the author of the given story. This will automatically update the id
     * of the story as the id is created from the title and author of the story.<br>
     * If a null value is given, the story's id will be set to an invalid id.
     * 
     * @param newAuthor the new author of story s
     * @param s the story to change the author of
     */
    public void setAuthor(Story s, String newAuthor) {
        s.setAuthor(newAuthor);
    }

    /**
     * Adds a fragment to the story's list of fragments. Behavior is unspecified
     * if either the story of fragment are null.
     * 
     * @param s the story to add a fragment to
     * @param f the list of fragments so a new one can be added
     */
    @Override
    public void addFragment(Story s, Fragment f) {
        List<Fragment> fragments = s.getFragments();
        fragments.add(f);
        s.setFragments(fragments);
    }

    /**
     * Removes the first occurrence of the given fragment in the story if there
     * is one and the value true is returned. If the given fragment does not
     * exist in the story, nothing is deleted and false is returned.
     * 
     * @param s the story to delete a fragment from
     * @param f fragment to be deleted
     * @return true if the fragment was deleted, otherwise false
     */
    @Override
    public boolean deleteFragment(Story s, Fragment f) {
        return s.removeFragment(f);
    }

    /**
     * Sets the given fragment at the given position in the given story.
     * Behavior is unknown if no fragment exists at this position.
     * 
     * @param s is the story where the fragment is contained
     * @param Pos is the Position of the fragment inside the story
     * @param f is the new fragment we wish to set at Pos
     */
    public void setFragmentAtLocation(Story s, int Pos, Fragment f) {
        s.getFragments().set(Pos, f);
    }

}
