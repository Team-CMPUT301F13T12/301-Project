
package ualberta.g12.adventurecreator;

/** Controls data from the Story Model */
public interface SController {
    /** Adds a fragment to the a story */
    public void addFragment(Fragment f);

    /**
     * Deletes a fragment from a story
     * 
     * @return true if fragment is deleted successfully else false
     */
    public boolean deleteFragment(int id);
}
