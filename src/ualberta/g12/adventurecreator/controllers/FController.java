
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.Fragment;

/**
 * Control of data in the Fragment model. Controls fragment modifications
 * within the selected fragment. Allows the user to add, delete or edit an
 * illustration, text or a choice to the current fragment.
 */
public interface FController {

    /**
     * Edits the title of the fragment
     * 
     * @param frag is the Fragment to be changed
     * @param newTitle the new title of the fragment
     */
    public void setTitle(Fragment frag, String newTitle);

 
    /**
     * removes empty FragmentPart from the fragment (for editing purposes only)
     * 
     * @param frag is the Fragment to be changed
     */
    public void removeEmptyPart(Fragment frag);

    /**
     * removes the FragmentPart specified by its partNum (it's number in the
     * displayorder) from the fragment
     * 
     * @param frag is the Fragment to be changed
     * @param partNum the fragmentPart number of the fragmentPart in the display
     *            order to be removed
     */
    public boolean deleteFragmentPart(Fragment frag, int partNum);

    // TODO: Implement annotations
    // public void addAnnotation(Fragment frag, Annotation annotation);

    // TODO: Implement adding media
    // public void addSound(Fragment frag, Sound sound, int dispNum);
    // public void addVideo(Fragment frag, Video video, int dispNum);

}
