
package ualberta.g12.adventurecreator.data;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Models a story that would be created by an Author. Contains a list of
 * Fragments as well as an author and a title.
 * <p>
 * A stories id must be unique when publishing it online. When publishing a
 * story, if there already a story with an identical ID that story will be
 * overwritten by the newly published story.<br>
 * A story's id is made up of the hashcode of the storyTitle appended with its
 * author. Whenever a story's title or author is changed, its id is updated
 * appropriately.<br>
 * If a story has a null author or title, it will be assigned the ID of
 * {@literal Story.INVALID_ID} and it will be impossible to upload this story
 * through normal means.
 */
@SuppressWarnings("serial")
public class Story extends SModel implements Serializable {

    /**
     * Signifies that a story with this id is invalid, usually meaning that
     * either its Title or Author is null.<br>
     * If a story has this id then it will not be publishable.
     */
    public static final int INVALID_ID = -1;

    private String storyTitle;
    private String author;
    private int id; // TODO: should be unique
    /** List of all fragments in the story in particular order */
    private List<Fragment> fragments;
    private int startFragPos; // start page

    /**
     * Create an Empty Story. This story will have the default start Fragment:
     * "Story Start Fragment" and a title and author of a blank string.
     */
    public Story() {
        this("", "");
    }

    /**
     * Create an Empty Story. This story will have the default start Fragment
     * "Story Start Fragment" and the title and author defined.
     */
    public Story(String title, String author) {
        this.startFragPos = 0;
        this.fragments = new LinkedList<Fragment>();
        Fragment frag = new Fragment();
        frag.setTitle("Story Start Fragment");
        this.storyTitle = "";
        this.author = "";
        updateId();
        this.addFragment(frag);
    }

    private void updateId() {
        if (this.storyTitle != null && this.author != null) {
            this.id = String.format("%s%s", this.storyTitle, this.author).hashCode();
        } else {
            this.id = INVALID_ID;
        }
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

    // // For merging stories (should happen when a choice is set to a page in
    // // another story)
    // public void afterPageLinkedToAnotherStory(Story newStory, Fragment
    // pageLinkedTo,
    // Choice choiceToSet) {
    // // adds all pages of newStory to the current story
    // for (int i = 0; i < newStory.getFragments().size(); i++) {
    // this.fragments.add(newStory.getFragments().get(i));
    // }
    // choiceToSet.setLinkedToFragment(pageLinkedTo);
    // }

    // // finds isolated pages and sets their isLinkedTo flag to false
    // // should be run before each time a list of pages in the story is
    // displayed
    // // or only before the pages are displayed and change flag is true.
    // // would need to create a change flag
    // public void findAndMarkIsolatedPages() {
    // LinkedList<Fragment> copyOfPages = new LinkedList<Fragment>();
    // // copies pages list
    // for (int i = 0; i < this.fragments.size(); i++) {
    // copyOfPages.add(fragments.get(i));
    // }
    // // removes all pages from copyOfPages that are referenced
    // for (int i = 0; i < this.fragments.size(); i++) {
    // for (int j = 0; j < fragments.get(i).getChoices().size(); j++) {
    // Fragment tempPage =
    // fragments.get(i).getChoices().get(j).getLinkedToFragment();
    // copyOfPages.remove(tempPage);
    // }
    // }
    // // only unreferenced pages remain
    // for (int i = 0; i < copyOfPages.size(); i++) {
    // copyOfPages.get(i).setLinkedTo(false);
    // }
    // }

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
