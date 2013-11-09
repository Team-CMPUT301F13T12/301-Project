
package ualberta.g12.adventurecreator;

import java.util.List;

/**
 * Controller for the stories that deal with the information regarding stories
 * in the application. Controls the ability to add or edit the title and author
 * of the stories, as well as add fragments to a selected story.
 */
public class StoryController implements SController {

    /**
     * sets the title of the selected story
     * 
     * @param newTitle the new title of story s
     * @param s the story to change the title of
     */
    public void setTitle(Story s, String newTitle) {
        s.setStoryTitle(newTitle);
    }

    /**
     * sets the author string of the selected story
     * 
     * @param newAuthor the new author of story s
     * @param s the story to change the author of
     */
    public void setAuthor(Story s, String newAuthor) {
        s.setAuthor(newAuthor);
    }

    @Override
    /**
     * adds a fragment to the selected story
     * 
     * @param s the story to add a fragment to
     * @param f the list of fragments so a new one can be added
     */
    public void addFragment(Story s, Fragment f) {
        List<Fragment> fragments = s.getFragments();
        fragments.add(f);
        s.setFragments(fragments);
    }

    @Override
    /**
     * deletes a fragment from the fragment list 
     * 
     * @param s the story to delete a fragment from
     * @param f fragment to be deleted 
     */
    public boolean deleteFragment(Story s, Fragment f) {
        return s.removeFragment(f);
    }

    /**
     * gets all fragments in a List from story s
     * 
     * @param s is the story to get fragments from
     * @return a List<Fragment> containing all fragments of the story
     */
    public List<Fragment> getFragments(Story s) {
        return s.getFragments();
    }

    /**
     * gets the fragment at a certain position of a story
     * 
     * @param s is the story to get the fragment from
     * @param fragPos is the position of the fragment in the story's fragment
     *            list
     * @return the Fragment as fragPos
     */
    public Fragment getFragmentAtPos(Story s, int fragPos) {
        return s.getFragments().get(fragPos);
    }

    /**
     * sets(replaces) a fragment at position Pos within the story
     * 
     * @param s is the story where the fragment is contained
     * @param Pos is the Position of the fragment inside the story
     * @param f is the new fragment we wish to set at Pos
     */
    public void setFragmentAtLocation(Story s, int Pos, Fragment f) {
        s.getFragments().set(Pos, f);
    }

}
