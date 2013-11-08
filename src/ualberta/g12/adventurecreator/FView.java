
package ualberta.g12.adventurecreator;

/**
 * Interface for all of the Views that Deal with the Fragment Model.<br>
 * Provides the method update(M) which is called whenever we the Fragment Model
 * has been updated.
 */
public interface FView<M> {
    /**
     * Is called whenever the M model has been updated. This should be
     * implemented to update our current display of the Fragment as it is now
     * out of sync.
     */
    public void update(M model);
}
