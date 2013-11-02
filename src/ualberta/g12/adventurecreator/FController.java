
package ualberta.g12.adventurecreator;

/** Control of data in the Fragment model */
public interface FController {
    public void editTitle(String t);

    public void addChoice(Choice c);

    public void deleteChoice(Choice c);
    // TODO: Implement annotations
    // public void addAnnotation(Annotation a);

    // Should these all be replaced by an addMedia(Media m) ?
    // TODO: Implement adding media
    // public void addPicture(Picture p);
    // public void addSound(Sound s);
    // public void addVideo(Video v);

}
