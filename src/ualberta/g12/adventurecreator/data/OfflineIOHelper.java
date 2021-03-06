
package ualberta.g12.adventurecreator.data;

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
 * storage.
 */
public class OfflineIOHelper {

    private String fileName = "myStories.sav";
    private Context storyContext;

    private static final boolean DEBUG = true;
    private static final String TAG = "OfflineIOHelper";

    /**
     * Sole constructor for the OfflineIOHelper class. Loads in context from the
     * calling application that is used for the FileInputStream and
     * FileOuputStreams.
     * 
     * @param context the context to load.
     */
    public OfflineIOHelper(Context context) {
        if (context == null) {
            if (DEBUG)
                Log.d(TAG, "Context was null");
        }
        this.storyContext = context;
    }

    /**
     * loads our offline stories located at fileName location
     * 
     * @return Arraylist<Story> the list of all saved offline stories
     */
    public StoryList loadOfflineStories() {
        StoryList stories = new StoryList();
        try {
            FileInputStream fis = storyContext.openFileInput(this.fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            stories = (StoryList) ois.readObject();
            if (stories == null)
                if (DEBUG)
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
     * Saves our offline stories
     * 
     * @param myStories the list of stories to be saved
     */
    public void saveOfflineStories(StoryList myStories) {
        if (myStories == null) {
            if (DEBUG)
                Log.d(TAG, "Saving a null StoryList");
            return;
        }
        try {
            FileOutputStream fos = storyContext
                    .openFileOutput(this.fileName, 0);
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
