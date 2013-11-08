
package ualberta.g12.adventurecreator;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Will Handle loading and saving of our offline(cached) stories from local
 * storage
 */
public class OfflineIOHelper {

    private String fileName = "myStories.sav";
    private Context storyContext;

    private static final String TAG = "OfflineIOHelper";

    /**
     * Sole constructor for the OfflineIOHelper class. Loads in context from the
     * calling application that is used for the FileInputStream and
     * FileOuputStreams.
     * 
     * @param context the context to load.
     */
    public OfflineIOHelper(Context context) {
        this.storyContext = context;
    }

    /**
     * loads our offline stories located at fileName location
     * 
     * @return Arraylist<Story>
     */
    public StoryList loadOfflineStories() {
        StoryList stories = new StoryList();
        try {

            FileInputStream fis = storyContext.openFileInput(this.fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            stories = (StoryList) ois.readObject();
            if (stories == null)
                Log.d(TAG, "StoryList read in is null");
            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stories;
    }

    /**
     * Saves ourOfflineStories
     * 
     * @param myStories which is the stories to be saved
     */

    // TODO make sure to make each model object serializable
    public void saveOfflineStories(StoryList myStories) {
        if (myStories == null)
            Log.d(TAG, "Saving a null StoryList");
        try {
            FileOutputStream fos = storyContext.openFileOutput(this.fileName, 0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myStories);
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
