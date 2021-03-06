
package ualberta.g12.adventurecreator.data;

import ualberta.g12.adventurecreator.views.FView;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A Fragment has a title, used to identify the fragment to the user, and a list
 * of FragmentParts. This list contains all content that the author wishes the
 * Fragment to display. Each FragmentPart represents a different content type,
 * (such text, illustrations, etc). The list is displayed in order whenever a
 * Fragment is viewed. The design pattern Null Object was used in this class. As
 * can be seen when an object of this class is instantiated all fields are
 * initialized to non-null values.
 * 
 * @see FragmentPart
 * @see Choice
 */
public class Fragment extends FModel<FView<?>> implements Serializable {

    /**
     * Serial UID for Lint
     */
    private static final long serialVersionUID = 6155560311789807407L;

    private String title;
    private List<FragmentPart> parts;
    private List<String> annotations;

    /**
     * Creates an empty fragment.
     */
    public Fragment() {
        title = "";
        parts = new LinkedList<FragmentPart>();
        annotations = new LinkedList<String>();
        annotations.add("");
        annotations.add("");
        annotations.add("");
    }

    /**
     * The title of a Fragment is used for a user to identify the Fragment.
     * 
     * @return title the title of the Fragment.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * The title of a Fragment is used for a user to identify the Fragment.
     * 
     * @param newTitle the string to change the title of the Fragment to.
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
        notifyViews();
    }

    /**
     * The list of FragmentParts contains all the content data for the fragment.
     * The list of FragmentParts is displayed when a fragment is viewed.
     * 
     * @return parts The list of FragmentParts for the Fragment.
     * @see FragmentPart
     */
    public List<FragmentPart> getParts() {
        return parts;
    }

    /**
     * Sets the list of FragmentParts in the Fragment to parts. The list of
     * FragmentParts contains all the content information for the fragment. The
     * list of FragmentParts is displayed when a fragment is viewed.
     * 
     * @param parts the new list of FragmentParts for the Fragment
     * @see FragmentPart
     */
    public void setParts(List<FragmentPart> parts) {
        this.parts = parts;
        notifyViews();
    }

    /**
     * Gets the annotations. There will always be 3 annotations in this list.
     * Each is for one of the annotation spots in FragmentViewActivity. Each
     * entry contains a path to a picture or the default "".
     * 
     * @return the annotations
     */
    public List<String> getAnnotations() {
        return annotations;
    }

    /**
     * Sets the annotations. There will always be 3 annotations in this list.
     * Each is for one of the annotation spots in FragmentViewActivity. Each
     * entry contains a path to a picture or the default "".
     * 
     * @param annotations the list of picture paths to set as annotations
     */
    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
        notifyViews();
    }

    /**
     * Writes the Fragment to an ObjectOutputStream.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.title);
        out.writeObject(this.parts);
        out.writeObject(this.annotations);
    }

    /**
     * Loads the Fragment from an ObjectInputStream
     * 
     * @param in the ObjectInputStream to read from
     */
    @SuppressWarnings(value = {
            "unchecked"
    })
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.title = (String) in.readObject();
        this.parts = (List<FragmentPart>) in.readObject();
        this.annotations = (List<String>) in.readObject();
    }
}
