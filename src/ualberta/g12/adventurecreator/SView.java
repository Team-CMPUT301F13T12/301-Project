
package ualberta.g12.adventurecreator;

/** A view that interacts the story model*/
public interface SView<M> {
    /** Called by the model to notify view of an update of the models Data. */
    public void update(M model);

}
