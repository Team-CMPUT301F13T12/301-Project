
package ualberta.g12.adventurecreator;

public interface SView<M> {
    /** Called by the model to notify view of an update of the models Data. */
    public void update(M model);

}
