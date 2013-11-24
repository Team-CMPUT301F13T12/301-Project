
package ualberta.g12.adventurecreator.data;

/**
 * A holder class that contains the Title and Author of a story
 */
public class TitleAuthor extends Story {
    /* These should never change as we are viewing the story */
    private final String title, author;
    private final int id;

    public TitleAuthor(String t, String a, int id) {
        this.title = t;
        this.author = a;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getId() {
        return this.id;
    }
}
