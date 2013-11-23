// From: http://stackoverflow.com/questions/9219023/best-way-to-serialize-deserialize-an-image-in-android
// By: diadyne 

package ualberta.g12.adventurecreator.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A serializable Image class that is used to save images to disk
 */
public class SerializableImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int NO_IMAGE = -1;

    private Bitmap image;

    /**
     * @return a Bitmap of the image
     */
    public Bitmap getImage() {
        return image;
    }

    /** @param image the Bitmap to set as this image */
    public void setImage(Bitmap image) {
        this.image = image;
    }

    /**
     * Writes ourself to the ObjectOutputStream provided.
     * 
     * @param out the ObjectOutputStream to write to
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        if (image != null) {
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            final byte[] imageByteArray = stream.toByteArray();
            out.writeInt(imageByteArray.length);
            out.write(imageByteArray);
        } else {
            out.writeInt(NO_IMAGE);
        }
    }

    /**
     * Load ourself from the ObjectInputStream provided.
     * 
     * @param in the ObjectInputStream to write to
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        final int length = in.readInt();

        if (length != NO_IMAGE) {
            final byte[] imageByteArray = new byte[length];
            in.readFully(imageByteArray);
            image = BitmapFactory.decodeByteArray(imageByteArray, 0, length);
        }
    }
}
