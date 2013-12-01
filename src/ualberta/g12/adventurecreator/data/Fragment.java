
package ualberta.g12.adventurecreator.data;

import ualberta.g12.adventurecreator.views.FView;

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
public class Fragment extends FModel<FView<?>> implements Serializable {

    /**
     * Serial UID for Lint
     */
    private static final long serialVersionUID = 6155560311789807407L;

    private static int NEW_FRAGMENT_ID = -1;
    private String title;
    private int id;
    private List<FragmentPart> parts;
    // private Annotation annotations;

    /**
     * No argument constructor for a Fragment. Initializes all of the Lists that
     * are used by a Fragment
     */
    public Fragment() {
        title = "";
        parts = new LinkedList<FragmentPart>();
    }

    /** @return the title of the fragment */
    public String getTitle() {
        return this.title;
    }

    /** @param the newTitle of the fragment */
    public void setTitle(String newTitle) {
        this.title = newTitle;
        notifyViews();
    }

    /** @return the id of the fragment */
    public int getId() {
        return this.id;
    }

    /** @param the new Id of the fragment */
    public void setId(int id) {
        this.id = id;
    }
    
    public List<FragmentPart> getParts() {
        return parts;
    }

    public void setParts(List<FragmentPart> parts) {
        this.parts = parts;
        notifyViews();
    }

    // public Annotation getAnnotations() {
    // return annotations;
    // }
    //
    // public void setAnnotations(Annotation annotations) {
    // this.annotations = annotations;
    // }

    /**
     * Writes ourself to an ObjectOutputStream.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(NEW_FRAGMENT_ID);
        out.writeObject(this.title);
        out.writeObject(this.id);
        out.writeObject(this.parts);
    }

    /**
     * Loads ourself from an ObjectInputStream
     * 
     * @param in the ObjectInputStream to read from
     */
    @SuppressWarnings(value = {
            "unchecked"
    })
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        NEW_FRAGMENT_ID = (Integer) in.readObject();
        this.title = (String) in.readObject();
        this.id = (Integer) in.readObject();
        this.parts = (List<FragmentPart>)in.readObject();
    }

}
