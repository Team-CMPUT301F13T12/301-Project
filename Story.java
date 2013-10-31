

import java.util.LinkedList;

public class Story {
	private String storyTitle;
	private String author;
	private LinkedList<StoryFragment> pages; //first index in pages is always the start page

	public Story() {
		this.storyTitle = "Add a title.";
		this.author = "Your pen name here";
		this.pages = new LinkedList<StoryFragment>();
	}
	
	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LinkedList<StoryFragment> getPages() {
		return pages;
	}

	public void addPage(StoryFragment newPage) {
		this.pages.add(newPage);
	}
	
	public void removePage(StoryFragment oldPage) {
		this.pages.remove(oldPage);
	}
	
	public void addLinkToNewPage(){
		
	}
	
	public void addLinkToPageInStory(StoryFragment currentPage, String choiceText, StoryFragment linkedToPage){
		
	}
	
	//For merging stories (should happen when a choice is set to a page in another story)
	public void afterPageLinkedToAnotherStory(Story newStory, StoryFragment pageLinkedTo, Choice choiceToSet){
		//adds all pages of newStory to the current story
		for(int i=0; i<newStory.getPages().size();i++){
			this.pages.add(newStory.getPages().get(i));
		}
		choiceToSet.setLinkedToPage(pageLinkedTo);
	}

	//finds isolated pages and sets their isLinkedTo flag to false
	//should be run before each time a list of pages in the story is displayed
	//or only before the pages are displayed and change flag is true.  
	//would need to create a change flag
	public void findAndMarkIsolatedPages(){
		LinkedList<StoryFragment> copyOfPages = new LinkedList<StoryFragment>();
		//copies pages list
		for(int i=0; i<this.pages.size(); i++){
			copyOfPages.add(pages.get(i));
		}
		//removes all pages from copyOfPages that are referenced
		for(int i=0; i<this.pages.size(); i++){
			for (int j=0; j<pages.get(i).getChoices().size(); j++){
				StoryFragment tempPage = pages.get(i).getChoices().get(j).getLinkedToPage();
				copyOfPages.remove(tempPage);
			}
		}
		//only unreferenced pages remain
		for(int i=0; i<copyOfPages.size();i++){
			copyOfPages.get(i).setLinkedTo(false);
		}
	}

}
