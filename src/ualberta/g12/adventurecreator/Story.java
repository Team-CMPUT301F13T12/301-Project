
package ualberta.g12.adventurecreator;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Models a story that would be created by an Author. Contains a list of
 * Fragments as well as an author and a title.
 */
public class Story extends SModel implements Serializable{
    
    private static int NEW_STORY_ID = -1;
    
    private String storyTitle;
    private String author;
    private int id = 1; // TODO: should be unique 
    private List<Fragment> fragments; // list of all fragments in story (no particular order)
    private int startFragPos;     // start page

    public Story() {
        this.startFragPos = 0;
        this.fragments = new LinkedList<Fragment>();
        Fragment frag = new Fragment();
        frag.setTitle("Story Start Fragmet");
        this.addFragment(frag);
    }

    public Story(String title, String author) {
        this();
        setStoryTitle(title);
        setAuthor(author);   
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
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

    public void setFragments(List<Fragment> f){
        this.fragments = f;
    }
    
    public void addLinkToNewPage() {

    }

    public void addLinkToPageInStory(Fragment currentPage, String choiceText, Fragment linkedToPage) {

    }

    // For merging stories (should happen when a choice is set to a page in
    // another story)
    public void afterPageLinkedToAnotherStory(Story newStory, Fragment pageLinkedTo,
            Choice choiceToSet) {
        // adds all pages of newStory to the current story
        for (int i = 0; i < newStory.getFragments().size(); i++) {
            this.fragments.add(newStory.getFragments().get(i));
        }
        choiceToSet.setLinkedToFragment(pageLinkedTo);
    }

    // finds isolated pages and sets their isLinkedTo flag to false
    // should be run before each time a list of pages in the story is displayed
    // or only before the pages are displayed and change flag is true.
    // would need to create a change flag
    public void findAndMarkIsolatedPages() {
        LinkedList<Fragment> copyOfPages = new LinkedList<Fragment>();
        // copies pages list
        for (int i = 0; i < this.fragments.size(); i++) {
            copyOfPages.add(fragments.get(i));
        }
        // removes all pages from copyOfPages that are referenced
        for (int i = 0; i < this.fragments.size(); i++) {
            for (int j = 0; j < fragments.get(i).getChoices().size(); j++) {
                Fragment tempPage = fragments.get(i).getChoices().get(j).getLinkedToFragment();
                copyOfPages.remove(tempPage);
            }
        }
        // only unreferenced pages remain
        for (int i = 0; i < copyOfPages.size(); i++) {
            copyOfPages.get(i).setLinkedTo(false);
        }
    }
    
    /**
     * will get id of the story (should be unique)
     * @return
     */
    public int getId(){
    	return this.id;
    }
    
    /**
     * will set id of the story (should be unique)
     * @param newId
     */
    public void setId(int newId){
    	this.id = newId;
    }
    
    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        out.writeObject(this.NEW_STORY_ID);
        out.writeObject(this.storyTitle);
        out.writeObject(this.author);
        out.writeObject(this.id);
        out.writeObject(this.fragments);
        out.writeObject(this.startFragPos);
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        this.NEW_STORY_ID = (Integer) in.readObject();
        this.storyTitle = (String) in.readObject();
        this.author = (String) in.readObject();
        this.id = (Integer) in.readObject();
        this.fragments = (List<Fragment>) in.readObject();
        this.startFragPos = (Integer) in.readObject();
    }

}
