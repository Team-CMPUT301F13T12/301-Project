
package ualberta.g12.adventurecreator;

/** Controls data from the Story Model */
public interface SController {

    /**
     * Adds a fragment to the a story
     * 
     * @param f the fragment to add to the story
     */
    public void addFragment(Fragment f);

    /**
     * Deletes a fragment from a story
     * 
     * @return true if fragment is deleted successfully else false
     * @param f the id of the fragment to delete
     */
    public boolean deleteFragment(Fragment f);
}
