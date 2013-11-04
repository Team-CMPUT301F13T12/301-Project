package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.util.LinkedList;
import java.util.List;

// TODO: parameterize to an FView
public class Fragment extends FModel{

    private static final int NEW_FRAGMENT_ID = -1;

    private String title;
    private int id;
    private List<String> textSegments;
    private List<Drawable> illustrations;
    // private List<Sound> sounds;
    // private List<Video> videos;
    private List<Choice> choices;
    private List<String> displayOrder; // Contains one character representations
    // of each type to display
    // True if at least one page references it, can be used a flag for isolated
    // pages
    // will have to be controlled from the story object
    private boolean isLinkedTo;

    // private Annotation annotations;

    public Fragment() {
        textSegments = new LinkedList<String>();
        illustrations = new LinkedList<Drawable>();
        choices = new LinkedList<Choice>();
        displayOrder = new LinkedList<String>();
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id ){
        this.id = id;
    }

    public List<String> getTextSegments() {
        return textSegments;
    }

    public void setTextSegments(List<String> textSegment) {
        this.textSegments = textSegment;
    }

    public List<Drawable> getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(List<Drawable> illustrations) {
        this.illustrations = illustrations;
    }

    // public List<Sound> getSounds() {
        // return sounds;
    // }
    //
    // public void setSounds(List<Sound> sounds) {
    // this.sounds = sounds;
    // }
    //
    // public List<Video> getVideos() {
    // return videos;
    // }
    //
    // public void setVideos(List<Video> videos) {
    // this.videos = videos;
    // }

    public List<Choice> getChoices() {
        return choices;
    }

    public void addChoice(Choice c){
        this.choices.add(c);
    }

    public void setChoices(List<Choice> newChoices) {
        this.choices = newChoices;
    }

    public boolean removeChoice(Choice oldChoice) {
        return this.choices.remove(oldChoice);
    }

    public List<String> getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(List<String> displayOrder) {
        // Does this belong here or in the StoryController
        this.displayOrder = displayOrder;
    }

    public boolean isLinkedTo() {
        return isLinkedTo;
    }

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

}
