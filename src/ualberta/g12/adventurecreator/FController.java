
package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

/** Control of data in the Fragment model */
public interface FController {

    /**
     * Edits the title of the fragment
     * 
     * @param t the new title of the fragment
     */

    public void editTitle(Fragment f,String t);
    
//  TODO
//    /**
//     * Edits the body text of the fragment
//     * 
//     * @param t the new body text of the fragment
//     */
//    public void edittextSegment(Fragment f, String b);

    /**
     * Adds illustration to a fragment
     * 
     * @param i the illustration to add to the fragment
     */
    public void addIllustration(Fragment f, Drawable i);

    /**
     * Adds a choice to the list of choices that a fragment has
     * 
     * @param c the choice to add to the list
     */
    public void addChoice(Fragment f, Choice c);

    /**
     * Removes a choice from the list of choices a fragment has
     * 
     * @param c the choice to remove
     * @return true is choice was removed else false
     */
    public boolean deleteChoice(Fragment f, Choice c);
    
    // TODO: Implement annotations
    // public void addAnnotation(Fragment f, Annotation a);

    // Should these all be replaced by an addMedia(Fragment f, Media m) ?
    // TODO: Implement adding media
    // public void addPicture(Fragment f, Picture p);
    // public void addSound(Fragment f, Sound s);
    // public void addVideo(Fragment f, Video v);

}
