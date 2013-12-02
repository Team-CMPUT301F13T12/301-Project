
package ualberta.g12.adventurecreator.data;

import java.io.Serializable;

/**
 * A FragmentPart can represent different forms of content for a Fragment. The
 * forms that FragmentPart can currently represent include; text segments,
 * illustrations, and choices. There is also a special type called empty. empty
 * is used for ensuring there is at least one entry in a Fragment's FragmentPart
 * list. Without at least one entry the used would be unable to create anymore
 * FragmentParts for the Fragment. The design pattern Null Object was used in
 * this class. As can be seen when an object of this class is instantiated all
 * fields are initialized to non-null values.
 */
public class FragmentPart implements Serializable {
    /**
     * Serial UID for Lint
     */
    private static final long serialVersionUID = -2587828619669927800L;

    private String type;
    private String data;
    private Choice choice;
    private int picSize;

    /**
     * Creates an empty FragmentPart of type. The FragmentPart's type is a flag
     * that tells what kind of data will be stored in the FragmentPart. The type
     * can be: "t" for text "c" for choice "i" for illustration "e" for an empty
     * part.
     * 
     * @param type they type of FragmentPart to create
     */
    public FragmentPart(String type) {
        this.type = type;
        this.data = "";
        this.choice = new Choice();
        this.picSize = 1;
    }

    /**
     * The FragmentPart's type is a flag that tells what kind of data will be
     * stored in the FragmentPart. The type can be: "t" for text "c" for choice
     * "i" for illustration "e" for an empty part.
     * 
     * @return type the type of the FragmentPart
     */
    public String getType() {
        return type;
    }

    /**
     * The data The data field is used to store data for the "t" (text) and "i"
     * (illustration) types. For text it stores the text that should be
     * displayed. For illustrations it stores the location of the picture that
     * it references.
     * 
     * @return data the data stored in a FragmentPart
     */
    public String getData() {
        return data;
    }

    /**
     * The data to set The data field should only be used for "t" (text) and "i"
     * (illustration) types. For text it stores the text that should be
     * displayed. For illustrations it stores the location of the picture that
     * it references.
     * 
     * @param data the string to set a FragmentPart's data field to
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * The choice field is used by type "c" (choice) FragmentParts to store a
     * Choice object. The Choice object is used when users are reading a story,
     * selecting it will take the user to the next Fragment, as indicated by the
     * Choice.
     * 
     * @return choice the Choice stored in a FragmentPart's choice field
     * @see Fragment
     * @see Choice
     */
    public Choice getChoice() {
        return choice;
    }

    /**
     * The choice field is used by type "c" (choice) FragmentParts to store a
     * Choice object. The Choice object is used when users are reading a story,
     * selecting it will take the user to the next Fragment, as indicated by the
     * Choice.
     * 
     * @param choice the Choice to be stored in the FragmenPart's choice field.
     * @see Fragment
     * @see Choice
     */
    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    /**
     * The choice field is used by type "i" (illustration) FragmentParts to
     * store an illustration size flag. The flag is used to determine whether
     * the picture should be displayed in a larger or smaller container.
     * 
     * @return picSize the flag stored the FragmentPart's picSize field
     */
    public int getPicSize() {
        return picSize;
    }

    /**
     * The choice field is used by type "i" (illustration) FragmentParts to
     * store an illustration size flag. The flag is used to determine whether
     * the picture should be displayed in a larger or smaller container.
     * 
     * @return picSize the flag to be stored the FragmentPart's picSize field
     */
    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }
}
