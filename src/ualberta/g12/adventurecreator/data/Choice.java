
package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;

/**
 * Used to implement, set, and link desired choices to each other. Allows the
 * user to link two fragments together so that they can be viewed and linked in
 * the reader's mode. The design pattern Null Object was used in this class. As
 * can be seen when an object of this class is instantiated all fields are
 * initialized to non-null values.
 */
public class Choice implements Serializable {
    /**
     * Serial Id for the Lint
     */
    private static final long serialVersionUID = -9049654917867777945L;
    private String choiceText;
    private int linkedToFragmentPos;
    private boolean isRandom;

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
     * @return isRandom the value of the Choice's isRandom field
     */
    public boolean getisRandom() {
        return this.isRandom;
    }

    /**
     * sets isRandom boolean variable that tells if a choice is a RANDOM type
     * choice which means it will randomly go to a different fragment each
     * time the choice is selected.
     * 
     * @param bool the value to the Choice's isRandom field
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

    /**
     * Writes the Choice to an ObjectOutputStream.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.choiceText);
        out.writeObject(this.linkedToFragmentPos);
        out.writeObject(this.isRandom);

    }

    /**
     * Loads the Choice from an ObjectInputStream
     * 
     * @param in the ObjectInputStream to read from
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.choiceText = (String) in.readObject();
        this.linkedToFragmentPos = (Integer) in.readObject();
        this.isRandom = (Boolean) in.readObject();
    }
}
