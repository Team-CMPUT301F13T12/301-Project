package ualberta.g12.adventurecreator;

import java.io.IOException;

public class FragmentPartIllustration extends FragmentPart<String> {
    String picPath;
    
    public FragmentPartIllustration(){
        this.picPath = "";
    }
    
    @Override
    public String getAttribute() {
        return picPath;
    }

    @Override
    public void setAttribute(String attr) {
        if (attr != null){
            picPath = attr;
        }
    }
    
    /**
     * Writes ourself to an ObjectOutputStream.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.picPath);
    }

    /**
     * Loads ourself from an ObjectInputStream
     * 
     * @param in the ObjectInputStream to read from
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.picPath = (String) in.readObject();
    }

}
