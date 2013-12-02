
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
     * @param s the Story to change the title of
     * @param newTitle the new title of story s
     * 
     */
    @Override
    public void setTitle(Story s, String newTitle) {
        s.setTitle(newTitle);
    }

    /**
     * Sets the author of the given story. This will automatically update the id
     * of the story as the id is created from the title and author of the story.<br>
     * If a null value is given, the story's id will be set to an invalid id.
     * 
     * @param s the Story to change the author of
     * @param newAuthor the new author of Story s
     * @param s the story to change the author of
     */
    @Override
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
     * Sets the given fragment at the given position in the given story.
     * Behavior is unknown if no fragment exists at this position.
     * 
     * @param s is the story where the fragment is contained
     * @param Pos is the Position of the fragment inside the story
     * @param f is the new fragment we wish to set at Pos
     */
    @Override
    public void setFragmentAtLocation(Story s, int Pos, Fragment f) {
        s.getFragments().set(Pos, f);
    }
}
