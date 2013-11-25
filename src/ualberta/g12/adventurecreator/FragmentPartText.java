package ualberta.g12.adventurecreator;

import java.io.IOException;

public class FragmentPartText extends FragmentPart<String>{
    String text;
    
    public FragmentPartText(){
        this.text = "";
    }

    @Override
    public String getAttribute() {
        return text;
    }

    @Override
    public void setAttribute(String attr) {
        if (attr != null){
            text = attr;
        }
    }
    
    /**
     * Writes ourself to an ObjectOutputStream.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.text);
    }

    /**
     * Loads ourself from an ObjectInputStream
     * 
     * @param in the ObjectInputStream to read from
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.text = (String) in.readObject();
    }
}
