package ualberta.g12.adventurecreator;

import java.io.IOException;
import java.util.List;

public class FragmentPartChoice extends FragmentPart<Choice> {
    Choice choice;
    
    public FragmentPartChoice(){
        this.choice = new Choice();
    }

    @Override
    public Choice getAttribute() {
        return choice;
    }

    @Override
    public void setAttribute(Choice attr) {
        if (attr != null){
            choice = attr;
        }
    }
    
    /**
     * Writes ourself to an ObjectOutputStream.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.choice);
    }

    /**
     * Loads ourself from an ObjectInputStream
     * 
     * @param in the ObjectInputStream to read from
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.choice = (Choice) in.readObject();
    }

}
