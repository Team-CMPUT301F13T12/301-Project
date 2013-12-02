
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.Fragment;

/**
 * Control of data in the Fragment model. Controls fragment modifications within
 * the selected fragment. Allows the user to add, delete or edit an
 * illustration, text or a choice to the current fragment.
 */
public interface FController {

    /**
     * Sets the title of the given Fragment to the given String, which should
     * not be null
     * 
     * @param frag is the Fragment to be changed
     * @param newTitle the new title of the fragment
     */
    public void setTitle(Fragment frag, String newTitle);

    /**
     * Removes an empty fragment part that was added to this Fragment. If none
     * exist, nothing happens.
     * 
     * @param frag is the Fragment to be changed
     */
    public void removeEmptyPart(Fragment frag);

    /**
     * Removes the FragmentPart specified by its partNum (it's number in the
     * displayorder) from the fragment. The behavior is unspecified if the
     * fragment doesn't exist or if there is no part at partNum
     * 
     * @param frag is the Fragment to be changed
     * @param partNum the fragmentPart number of the fragmentPart in the display
     *            order to be removed
     */
    public boolean deleteFragmentPart(Fragment frag, int partNum);

}
