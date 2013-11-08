
package ualberta.g12.adventurecreator;

import java.util.List;

/**
 * Controller that dictates all fragment methods. Is responsible for all
 * modification within a fragment. Contains code that allows the user to add,
 * edit or delete, the illustrations, text or choices within a fragment.
 */

public class FragmentController implements FController {

    @Override
    /**
     * Edit the current title in place of the fragment
     * 
     * @param frag  fragment reference corresponding to the current fragment
     * @param newTitle  title that has been previously stated
     */
    public void editTitle(Fragment frag, String newTitle) {
        frag.setTitle(newTitle);
    }

    @Override
    /**
     * Allows the user to add a text segment to the current fragment.
     * 
     * @param frag  fragment reference corresponding to the current fragment
     * @param textSegment   reference to the text of the current segment
     * @param dispNum   refers to the position of the text segment 
     */
    public boolean addTextSegment(Fragment frag, String textSegment, int dispNum) {
        List<String> textSegments = frag.getTextSegments();
        List<String> displayOrder = frag.getDisplayOrder();

        // check for invalid dispNum
        if (displayOrder.size() < dispNum)
            return false;

        // Insert the text segment
        int textSegNum = 0;
        for (int i = 0; i < dispNum; i++) {
            if (displayOrder.get(i).equals("t"))
                textSegNum++;
        }
        if (textSegments.size() == textSegNum) {
            textSegments.add(textSegment);
        } else {
            textSegments.add(textSegNum, textSegment);
        }

        // insert t into corresponding spot in display order
        displayOrder.add(dispNum, "t");

        frag.setDisplayOrder(displayOrder);
        frag.setTextSegments(textSegments);

        return true;
    }

    /**
     * Removes the text segment at the given dispNum returns true if successful
     * 
     * @param frag fragment reference corresponding to the current fragment
     * @param dispNum refers to the position of the text segment
     */
    private boolean deleteTextSegment(Fragment frag, int dispNum) {
        List<String> textSegments = frag.getTextSegments();
        List<String> displayOrder = frag.getDisplayOrder();

        // not a text segment at dispNum
        if (!displayOrder.get(dispNum).equals("t"))
            return false;

        int textSegNum = 0;
        for (int i = 0; i < dispNum; i++) {
            if (displayOrder.get(i).equals("t"))
                textSegNum++;
        }

        textSegments.remove(textSegNum);
        frag.setTextSegments(textSegments);
        displayOrder.remove(dispNum);
        frag.setDisplayOrder(displayOrder);
        return true;
    }

    @Override
    /**
     * allows the author to add an illustration into a fragment
     * 
     * @param frag  fragment reference corresponding to the current fragment
     * @param illustration  refers to the illustration that has been previously saved 
     * @param dispNum   refers to the position of the text segment
     * 
     */
    public boolean addIllustration(Fragment frag, String illustration, int dispNum) {
        List<String> illustrations = frag.getIllustrations();
        List<String> displayOrder = frag.getDisplayOrder();

        System.out.println("add ill start2");
        // check for invalid dispNum
        if (displayOrder.size() < dispNum)
            return false;
        System.out.println("ill 3");
        // Insert the text segment
        int illNum = 0;
        for (int i = 0; i < dispNum; i++) {
            if (displayOrder.get(i).equals("i"))
                illNum++;
        }
        System.out.println("illNum " + illNum);
        if (illustrations.size() == illNum) {
            illustrations.add(illustration);
        } else {
            illustrations.add(illNum, illustration);
        }

        // insert t into corresponding spot in display order
        displayOrder.add(dispNum, "i");

        frag.setDisplayOrder(displayOrder);
        frag.setIllustrations(illustrations);
        System.out.println("ill disord " + displayOrder.toString());
        System.out.println("ill size " + illustrations.size());

        return true;
    }

    /**
     * Removes the illustration at the given dispNum returns true if successful
     * 
     * @param frag fragment reference corresponding to the current fragment
     * @param dispNum refers to the position of the text segment
     */
    private boolean deleteIllustration(Fragment frag, int dispNum) {
        List<String> illustrations = frag.getIllustrations();
        List<String> displayOrder = frag.getDisplayOrder();

        // not an illustration at dispNum
        if (!displayOrder.get(dispNum).equals("i"))
            return false;

        int illustrationNum = 0;
        for (int i = 0; i < dispNum; i++) {
            if (displayOrder.get(i).equals("i"))
                illustrationNum++;
        }

        illustrations.remove(illustrationNum);
        frag.setIllustrations(illustrations);
        displayOrder.remove(dispNum);
        frag.setDisplayOrder(displayOrder);
        return true;
    }

    // @Override
    // public void addSound(Fragment frag, Sound sound){
    // LinkedList<Sound> sounds = frag.getSounds();
    // sounds.add(sound);
    // frag.setSounds(sounds);
    // LinkedList<String> displayOrder = frag.getDisplayOrder();
    // displayOrder.add("s");
    // frag.setDisplayOrder(displayOrder);
    // }
    //
    // @Override
    // public void addVideo(Fragment frag, Video video){
    // LinkedList<Video> videos = frag.getVideos();
    // videos.add(video);
    // frag.setVideos(videos);
    // LinkedList<String> displayOrder = frag.getDisplayOrder();
    // displayOrder.add("v");
    // frag.setDisplayOrder(displayOrder);
    // }
    //

    @Override
    /**
     * Allows the user to add a choice to the fragment. This will allow the user to link two 
     * fragments together.  
     * 
     * @param frag  fragment reference corresponding to the current fragment
     * @param cho   reference to the old choice within that position (null if new)
     */
    public void addChoice(Fragment frag, Choice cho) {
        List<Choice> choices = frag.getChoices();
        choices.add(cho);
        frag.setChoices(choices);
        List<String> displayOrder = frag.getDisplayOrder();
        displayOrder.add("c");
        frag.setDisplayOrder(displayOrder);
    }

    /**
     * Removes the Choice at the given dispNum returns true if successful
     * 
     * @param frag fragment reference corresponding to the current fragment
     * @param dispNum refers to the position of the text segment
     */
    public boolean deleteChoice(Fragment frag, int dispNum) {
        List<String> textSegments = frag.getTextSegments();
        List<String> displayOrder = frag.getDisplayOrder();

        // not a text segment at dispNum
        if (!displayOrder.get(dispNum).equals("c"))
            return false;

        int choiceNum = 0;
        for (int i = 0; i < dispNum; i++) {
            if (displayOrder.get(i).equals("c"))
                choiceNum++;
        }

        textSegments.remove(choiceNum);
        frag.setTextSegments(textSegments);
        displayOrder.remove(dispNum);
        frag.setDisplayOrder(displayOrder);
        return true;
    }

    @Override
    /**
     * adds a new element into the listview so that a segment can be added.  
     * 
     * @param frag  fragment reference corresponding to the current fragment
     */
    public void addEmptyPart(Fragment frag) {
        List<String> displayOrder = frag.getDisplayOrder();
        displayOrder.add("e");
        frag.setDisplayOrder(displayOrder);
    }

    @Override
    /**
     * deletes the selected segment when the user desires to delete a segment. 
     * 
     * @param frag  fragment reference corresponding to the current fragment
     */
    public void removeEmptyPart(Fragment frag) {
        List<String> displayOrder = frag.getDisplayOrder();
        displayOrder.remove("e");
        frag.setDisplayOrder(displayOrder);
    }

    // @Override
    // public void addAnnotation(Fragment frag, Annotation annotation){
    // Annotation annotations = frag.getAnnotations();
    // annotations.addAnnotation(annotation);
    // frag.setAnnotations(annotations);
    // }

    /**
     * Deletes a fragment part which can be text, illustration, choice , or
     * empty from our "fragments parts".
     * 
     * @param frag is the fragment we wish to remove parts from
     * @param partNum contains the part number of the part we wish to remove
     */
    @Override
    public void deleteFragmentPart(Fragment frag, int partNum) {
        List<String> displayOrder = frag.getDisplayOrder();
        // check for invalid partNum
        if (partNum >= displayOrder.size())
            return;

        String type = displayOrder.get(partNum);

        if (type.equals("t"))
            deleteTextSegment(frag, partNum);
        else if (type.equals("i"))
            deleteIllustration(frag, partNum);
        else if (type.equals("c"))
            deleteChoice(frag, partNum);
        else if (type.equals("e"))
            removeEmptyPart(frag);

    }

    /**
     * Provides the display order of the fragment parts,which is how they are
     * listed when we view them
     * 
     * @param f is the fragment to be called
     * @return List<String> which contains the display order
     */
    public List<String> getDisplayOrder(Fragment f) {
        return f.getDisplayOrder();
    }

    /**
     * Retrieves the display type of the fragment part at Pos
     * 
     * @param f is the fragment we want to get display type of
     * @param Pos is the position of the fragment part
     * @return A string that signals the display type i.e "e","c","t" or "i"
     */
    public String getDisplayOrderAtPos(Fragment f, int Pos) {
        return f.getDisplayOrder().get(Pos);
    }

    /**
     * Helps retrieve the position the fragment a particular choice is lined to
     * 
     * @param f is the fragment that the choice is contained in
     * @param Pos is the position of the choice
     * @return int which is the position of the linked to fragment in our
     *         fragments list
     */
    public int getLinkedToFragmentPosOfChoice(Fragment f, int Pos) {
        return f.getChoices().get(Pos).getLinkedToFragmentPos();
    }

    /**
     * getTextSegments provides all text segments in the fragments parts
     * 
     * @param f is the fragment we are looking for
     * @return List<String> of text fragments
     */
    public List<String> getTextSegments(Fragment f) {
        return f.getTextSegments();
    }

    /**
     * changes the Id of a fragment at position id
     * 
     * @param f is the fragment we wish to change the id to
     * @param id is the id we wish to associciate with the fragment
     */
    public void changeId(Fragment f, int id) {
        f.setId(id);
    }

    /**
     * Updates the choice at position POS in the choice list
     * 
     * @param f is the fragment which contains the choice
     * @param Pos is the position of the choice in the choice list
     * @param text is the text we wish to updates
     */
    public void setChoiceTextAtPos(Fragment f, int Pos, String text) {
        f.getChoices().get(Pos).setChoiceText(text);
    }

    /**
     * Updates the position of the linked fragment in our choices
     * 
     * @param f is the fragment the choice is contained int
     * @param Pos is the position of the choice
     * @param linkedPos is the poisition of the linked fragment in the fragment
     *            list
     */
    public void setLinkedFragmentOfChoiceAtPos(Fragment f, int Pos, int linkedPos) {
        f.getChoices().get(Pos).setLinkedToFragmentPos(linkedPos);
    }

}
