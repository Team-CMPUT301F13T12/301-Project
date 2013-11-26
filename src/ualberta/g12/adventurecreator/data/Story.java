
package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Models a story that would be created by an Author. Contains a list of
 * Fragments as well as an author and a title.
 */
public class Story extends SModel implements Serializable {

    public static final int INVALID_ID = -1;

    private String storyTitle;
    private String author;
    private int id; // should be unique
    private List<Fragment> fragments; // list of all fragments in story (no
                                      // particular order)
    private int startFragPos; // start page

    public Story() {
        this.startFragPos = 0;
        this.fragments = new LinkedList<Fragment>();
        Fragment frag = new Fragment();
        frag.setTitle("Story Start Fragment");

        this.storyTitle = "";
        this.author = "";
        updateId();
        this.addFragment(frag);
    }

    public Story(String title, String author) {
        this();
        setTitle(title);
        setAuthor(author);
        updateId();
    }

    private void updateId() {
       
        int newId;
        if (this.storyTitle != null && this.author != null) {
            newId = String.format("%s%s", this.storyTitle, this.author).hashCode();
        } else {
            newId = INVALID_ID;
        }
        
        this.id = newId;
    }

    public String getTitle() {
        return storyTitle;
    }

    public void setTitle(String storyTitle) {
        this.storyTitle = storyTitle;
        updateId();
    }

    public int getStartFragPos() {
        return startFragPos;
    }

    public void setStartFragPos(int startFragPos) {
        this.startFragPos = startFragPos;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        updateId();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void addFragment(Fragment newFragment) {
        this.fragments.add(newFragment);
    }

    public boolean removeFragment(Fragment oldFragment) {
        return this.fragments.remove(oldFragment);
    }

    public void setFragments(List<Fragment> f) {
        this.fragments = f;
    }

    public void addLinkToNewPage() {

    }

    public void addLinkToPageInStory(Fragment currentPage, String choiceText, Fragment linkedToPage) {

    }

    /**
     * will get id of the story (should be unique)
     * 
     * @return id of the story
     */
    public int getId() {
        return this.id;
    }

    /*
     * Users are not allowed to set ids, it is done automatically by the system
     */

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(this.storyTitle);
        out.writeObject(this.author);
        out.writeObject(this.id);
        out.writeObject(this.fragments);
        out.writeObject(this.startFragPos);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.storyTitle = (String) in.readObject();
        this.author = (String) in.readObject();
        this.id = (Integer) in.readObject();
        this.fragments = (List<Fragment>) in.readObject();
        this.startFragPos = (Integer) in.readObject();
    }

    @Override
    /**
     *  Changes our story to a string (JSON) !
     */
    public String toString() {
        return "Recipe [id=" + id + ", storyTitle=" + storyTitle + ", author=" + author
                + ", fragments="
                + fragments + ", startFragPos=" + startFragPos + "]";

    }

}
