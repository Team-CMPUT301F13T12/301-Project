
package ualberta.g12.adventurecreator.views;

/**
 * An interface that all OnlineViews implement in order to be properly updated
 * when the online task has downloaded all of the story titles and authors to
 * list.
 */
public interface OView<M> {

    /**
     * Called when the task downloading all of the online story titles and
     * authors has completed.<br>
     * Should be implemented in a matter that will load all of the stories onto
     * the screen and be able to hanlde when the download fails.
     */
    public void update(M model);
}
