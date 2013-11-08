
package ualberta.g12.adventurecreator;


/** Control of data in the Fragment model */
public interface FController {

    /**
     * Edits the title of the fragment
     * 
     *  @param frag is the Fragment to be changed
     * @param newTitle the new title of the fragment
     */
    public void editTitle(Fragment frag, String newTitle);
    
    /**
     * Adds textSegment to a fragment at specified position
     * 
     * @param frag is the Fragment to be changed
     * @param textSegment is the textSegment to add to the fragment
     * @param dispNum is position that the textSegment will be inserted
     */
    public boolean addTextSegment(Fragment frag, String textSegment, int dispNum);
    
    /**
     * Adds illustration to a fragment at specified position
     * 
     * @param frag is the Fragment to be changed
     * @param textSegment is the textSegment to add to the fragment
     * @param dispNum is position that the textSegment will be inserted
     */
    public boolean addIllustration(Fragment frag, String illustration, int dispNum);

    /**
     * Adds a choice to the list of choices that a fragment has
     * 
     * @param frag is the Fragment to be changed
     * @param cho the choice to add to the list
     */
    public void addChoice(Fragment frag, Choice cho);
    
    /**
     * Adds an empty FragmentPart to the fragment 
     * (for editing purposes only)
     * 
     * @param frag is the Fragment to be changed
     */
    public void addEmptyPart(Fragment frag);
    
    /**
     * removes empty FragmentPart from the fragment 
     * (for editing purposes only)
     * 
     * @param frag is the Fragment to be changed
     */
    public void removeEmptyPart(Fragment frag);
    
    /**
     * removes the FragmentPart specified by its partNum 
     * (it's number in the displayorder) from the fragment 
     * 
     * @param frag is the Fragment to be changed
     * @param partNum the fragmentPart number of the 
     * fragmentPart in the display order to be removed
     */
    public void deleteFragmentPart(Fragment frag, int partNum);

    
    // TODO: Implement annotations
    // public void addAnnotation(Fragment frag, Annotation annotation);

    // TODO: Implement adding media
    // public void addSound(Fragment frag, Sound sound, int dispNum);
    // public void addVideo(Fragment frag, Video video, int dispNum);

}