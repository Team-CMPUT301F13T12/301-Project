package ualberta.g12.adventurecreator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import android.content.Context;


/**
 * 
 * Will Handle loading and saving of our offline(cached) stories from local storage
 *
 */
public class OfflineIOHelper {
	
	private String fileName = "myStories.sav";
	private Context storyContext;
	
	public OfflineIOHelper(Context context){
		storyContext = context;
	}
	
	/**
	 * loads our offline stories located at fileName location
	 * @return Arraylist<Story>
	 * 
	 *
	 */
	public ArrayList<Story> loadOfflineStories() {
		ArrayList<Story> temp = new ArrayList<Story>();
		try {

			FileInputStream fis = storyContext.openFileInput(this.fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Story one = (Story) ois.readObject();
			while (temp != null) {
				temp.add(one);
				one = (Story) ois.readObject();
			}


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
		return temp;
	}
	
	/**
	 * Saves ourOfflineStories 
	 * @param myStories
	 */
	
	// TODO make sure to make each model object serializable
	public void saveOfflineStories(ArrayList<Story> myStories) {
		try {
			FileOutputStream fos = storyContext.openFileOutput(this.fileName,0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Story aStory : myStories) {
				oos.writeObject(aStory);
			}
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
