package ualberta.g12.adventurecreator;

import java.io.IOException;
import java.io.Serializable;

public class Choice implements Serializable{
    private String choiceText;
    private int linkedToFragmentPos;
    //private Fragment linkedToFragment;

    //default settings for a new choice
    public Choice() {
        this.choiceText = "";
        //this.linkedToFragment = null;  //always check before linking from a choice that likedToPage != null
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    /**
     * @return the linkedToFragmentPos
     */
    public int getLinkedToFragmentPos() {
        return linkedToFragmentPos;
    }

    /**
     * @param linkedToFragmentPos the linkedToFragmentPos to set
     */
    public void setLinkedToFragmentPos(int linkedToFragmentPos) {
        this.linkedToFragmentPos = linkedToFragmentPos;
    }

    //	public Fragment getLinkedToFragment() {
    //		return linkedToFragment;
    //	}
    //
    //	public void setLinkedToFragment(Fragment linkedToFragment) {
    //		this.linkedToFragment = linkedToFragment;
    //		linkedToFragment.setLinkedTo(true); //page is definitely linked to now
    //	}

    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        out.writeObject(this.choiceText);
        //out.writeObject(this.linkedToFragment);
        out.writeObject(this.linkedToFragmentPos);

    }


    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        this.choiceText = (String) in.readObject();
        //this.linkedToFragment = (Fragment) in.readObject();
        this.linkedToFragmentPos = (Integer) in.readObject();
    }
}
