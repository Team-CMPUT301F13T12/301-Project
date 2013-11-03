
package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.util.List;

// TODO: parameterize to an FView
public class Fragment extends FModel{
    
    private static final int NEW_FRAGMENT_ID = -1;
    
    private String title;
    private String bodyText;
    private int id;
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
        /*
         * Should this be done here? I feel that this should be moved to the
         * view that displays a new fragment as the default text or hint.
         * - Chris
         */
        this("Choose a Title", "Story body here.");
    }

    public Fragment(String title, String body) {
        setTitle(title);
        setBodyText(body);
        initChoices();
        isLinkedTo = true;
    }

    /** Each fragment should have one default choice */
    private void initChoices() {
        // TODO: intialize the new choice
    	
    	// i dont think so actually because if a fragment doesnt have a choice then it would be an ending!
    	// -Vincent 
        addChoice(new Choice());
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public List<Drawable> getIllustrations() {
        return this.illustrations;
    }

    public boolean removeIllustration(Drawable i){
        return this.illustrations.remove(i);
    }
    
    public boolean addIllustration(Drawable i){
        return this.illustrations.add(i);
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
