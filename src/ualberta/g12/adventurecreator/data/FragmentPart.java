package ualberta.g12.adventurecreator.data;

import java.io.Serializable;

public class FragmentPart implements Serializable{
    private String type;
    private String data;
    private Choice choice;
    private int picSize;
    
    /**
     * @param type
     * Creates an empty FragmentPart of type.
     * type can be:
     * "t" for text
     * "c" for choice
     * "i" for illustration
     * "e" for an empty part
     */
    public FragmentPart(String type){
        this.type=type;
        this.data = "";
        this.choice = new Choice();
    }
    /**
     * @return the type
     * the type can be 
     * "t" for text
     * "c" for choice
     * "i" for illustration
     * "e" for an empty part
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     * type should be either
     * "t" for text
     * "c" for choice
     * "i" for illustration or
     * "e" for an empty part
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the data
     * The data field is used to store data for the
     * "t" (text) and "i" (illustration) types. 
     * For text it stores the text that should be 
     * displayed.  For illustrations it stores the
     * location of the picture that it references.
     */
    public String getData() {
        return data;
    }
    /**
     * @param data the data to set
     * The data field should only be used for 
     * "t" (text) and "i" (illustration) types. 
     * For text it stores the text that should be 
     * displayed.  For illustrations it stores the
     * location of the picture that it references.
     */
    public void setData(String data) {
        this.data = data;
    }
    /**
     * @return the choice
     * This returns the choice referenced by the
     * FragmentPart.  Should only be used by type 
     * "c" (choice) FragmentParts.
     */
    public Choice getChoice() {
        return choice;
    }
    /**
     * @param choice the choice to set
     * Sets the choice for the FragmenPart.
     * Should only by used on type "c" (choice)
     * FragmentParts.
     */
    public void setChoice(Choice choice) {
        this.choice = choice;
    }
    
    /**
     * @return the picSize
     */
    public int getPicSize() {
        return picSize;
    }
    /**
     * @param picSize the picSize to set
     */
    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }
}
