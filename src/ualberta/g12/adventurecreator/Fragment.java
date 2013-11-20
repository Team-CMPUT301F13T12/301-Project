
package ualberta.g12.adventurecreator;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

// TODO: parameterize to an FView
/**
 * Saves and returns all the corresponding fragment information upon fragment
 * saving or fragment recall. Will Store the parameters within the fragment and
 * will be unique to that fragment.
 */
public class Fragment extends FModel implements Serializable {

    private static int NEW_FRAGMENT_ID = -1;

    private String title;
    private int id;
    private List<FragmentPart<?>> parts;
    
    /**
     * True if at least one page references it, can be used as a flag for
     * isolated pages.
     */
    private boolean isLinkedTo;

    // private Annotation annotations;

    /**
     * No argument constructor for a Fragment. Initializes all of the Lists that
     * are used by a Fragment
     */
    public Fragment() {
        title = "";
        parts = new LinkedList<FragmentPart<?>>();
    }

    /** @return the title of the fragment */
    public String getTitle() {
        return this.title;
    }

    /** @param the newTitle of the fragment */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /** @return the id of the fragment */
    public int getId() {
        return this.id;
    }

    /** @param the new Id of the fragment */
    public void setId(int id) {
        this.id = id;
    }

    /** @return if the fragment is linked to something else */
    public boolean isLinkedTo() {
        return isLinkedTo;
    }

    /** @param the new linkage of the fragment */
    public void setLinkedTo(boolean isLinkedTo) {
        this.isLinkedTo = isLinkedTo;
    }

    // public Annotation getAnnotations() {
    // return annotations;
    // }
    //
    // public void setAnnotations(Annotation annotations) {
    // this.annotations = annotations;
    // }

//    /**
//     * Writes ourself to an ObjectOutputStream.
//     * 
//     * @param out the ObjectOutputStream to write to
//     */
//    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
//        out.writeObject(this.NEW_FRAGMENT_ID);
//        out.writeObject(this.title);
//        out.writeObject(this.id);
//        out.writeObject(this.textSegments);
//        out.writeObject(this.illustrations);
//        // out.writeObject(this.sounds);
//        // out.writeObject(this.videos);
//        out.writeObject(this.choices);
//        out.writeObject(this.displayOrder);
//        out.writeObject(this.isLinkedTo);
//    }
//
//    /**
//     * Loads ourself from an ObjectInputStream
//     * 
//     * @param in the ObjectInputStream to read from
//     */
//    private void readObject(java.io.ObjectInputStream in) throws IOException,
//            ClassNotFoundException {
//        this.NEW_FRAGMENT_ID = (Integer) in.readObject();
//        this.title = (String) in.readObject();
//        this.id = (Integer) in.readObject();
//        this.textSegments = (List<String>) in.readObject();
//        this.illustrations = (List<String>) in.readObject();
//        // this.sounds = (LinkedList<>) in.readObject();
//        // this.videos = (LinkedList<>) in.readObject();
//        this.choices = (List<Choice>) in.readObject();
//        this.displayOrder = (List<String>) in.readObject();
//        this.isLinkedTo = (Boolean) in.readObject();
//    }

    public List<FragmentPart<?>> getParts() {
        return parts;
    }

    public void setParts(List<FragmentPart<?>> parts) {
        this.parts = parts;
    }
}
