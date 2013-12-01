
package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;

/**
 * Used to implement, set, and link desired choices to each other. Allows the
 * user to link two fragments together so that they can be viewed and linked in
 * the reader's mode.
 */
public class Choice implements Serializable {
    /**
     * Serial Id for the Lint
     */
    private static final long serialVersionUID = -9049654917867777945L;
    private String choiceText;
    private int linkedToFragmentPos;
    private boolean isRandom;

    // default settings for a new choice

    /**
     * default setting given to a newly implemented choice. The setting will be
     * null at first but will be checked after the choice is finished being
     * implemented.
     */
    public Choice() {
        this.choiceText = "";
        this.linkedToFragmentPos = -1;
        this.isRandom = false;
    }

    /**
     * Called to return the text of the choice that the user reads.
     * 
     * @return choiceText returns the text within the choice button
     */
    public String getChoiceText() {
        return choiceText;
    }

    /**
     * obtains the isRandom boolean variable that tells if a choice is a RANDOM
     * type choice which means it will go to some other choice
     * 
     * @return
     */
    public boolean getisRandom() {
        return this.isRandom;
    }

    /**
     * sets isRandom boolean variable that tells if a choice is a RANDOM type
     * choice which means it will go to some other choice
     */
    public void setisRandom(boolean bool) {
        this.isRandom = bool;
    }

    /**
     * Sets the text for the choice to be displayed as an option.
     * 
     * @param choiceText text that has been entered by the user
     */
    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    /**
     * Called to return the position of a fragment within the list.
     * 
     * @return linkToFragmentPos the linkedToFragmentPosition
     */
    public int getLinkedToFragmentPos() {
        return linkedToFragmentPos;
    }

    /**
     * Called to set the selected fragment to link with another fragment so that
     * choices can be implemented to take you from one fragment to another.
     * 
     * @param linkedToFragmentPos the linkedToFragmentPos to set
     */
    public void setLinkedToFragmentPos(int linkedToFragmentPos) {
        this.linkedToFragmentPos = linkedToFragmentPos;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.choiceText);
        out.writeObject(this.linkedToFragmentPos);
        out.writeObject(this.isRandom);

    }

    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.choiceText = (String) in.readObject();
        this.linkedToFragmentPos = (Integer) in.readObject();
        this.isRandom = (Boolean) in.readObject();
    }
}
