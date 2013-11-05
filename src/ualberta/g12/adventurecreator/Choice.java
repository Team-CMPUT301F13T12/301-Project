package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Choice implements Serializable{
	private String choiceText;
	private Fragment linkedToFragment;
	
	//default settings for a new choice
	public Choice() {
		this.choiceText = "Change My Text";
		this.linkedToFragment = null;  //always check before linking from a choice that likedToPage != null
	}

	public String getChoiceText() {
		return choiceText;
	}

	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}

	public Fragment getLinkedToFragment() {
		return linkedToFragment;
	}

	public void setLinkedToFragment(Fragment linkedToFragment) {
		this.linkedToFragment = linkedToFragment;
		linkedToFragment.setLinkedTo(true); //page is definitely linked to now
	}
	
	  private void writeObject(java.io.ObjectOutputStream out) throws IOException{
	        out.writeObject(this.choiceText);
	        out.writeObject(this.linkedToFragment);

	    }
	    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
	        this.choiceText = (String) in.readObject();
	        this.linkedToFragment = (Fragment) in.readObject();
	    }
}
