
package ualberta.g12.adventurecreator.views;

/**
 * All interfaces that deal with the StoryList Model must implement me. Provides
 * the necessary methods for all views that display the StoryList data to
 * update.
 */
public interface LView<M> {
    /**
     * Called whenever the Model has been changed, allows us to update ourself.
     * 
     * @param model the model whose data has changed
     */
    public void update(M model);

}
