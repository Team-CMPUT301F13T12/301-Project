
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;

import android.util.Log;

import java.io.File;
import java.util.List;

/**
 * Controller that dictates all fragment methods. Is responsible for all
 * modification within a fragment. Contains code that allows the user to add,
 * edit or delete, the illustrations, text or choices within a fragment.
 */

public class FragmentController implements FController {

    private static final String TAG = "FragmentController";

    @Override
    /**
     * Edit the current title in place of the fragment
     * 
     * @param frag  fragment reference corresponding to the current fragment
     * @param newTitle  title that has been previously stated
     */
    public void setTitle(Fragment frag, String newTitle) {
        frag.setTitle(newTitle);
    }

    public FragmentPart addNewFragmentPart(Fragment frag, String type, int pos){
        FragmentPart newPart = new FragmentPart(type);
        List<FragmentPart> parts = frag.getParts();
        try{
            parts.add(pos, newPart);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
        frag.setParts(parts);
        return newPart;
    }
    
    public void setFragmentPartData(Fragment frag, FragmentPart part, String data){
        part.setData(data);
        frag.setParts(frag.getParts());
    }
    
    public void setFragmentPartChoice(Fragment frag, FragmentPart part, Choice choice){
        part.setChoice(choice);
        frag.setParts(frag.getParts());
    }
    
    public void setFragmentPartPicSize(Fragment frag, FragmentPart part, int picSize){
        part.setPicSize(picSize);
        frag.setParts(frag.getParts());
    }
    
    /**
     * Deletes a fragment part which can be text, illustration, choice , or
     * empty from our "fragments parts".
     * 
     * @param frag is the fragment we wish to remove parts from
     * @param partNum contains the part number of the part we wish to remove
     */
    @Override    
    public boolean deleteFragmentPart(Fragment frag, int partNum){
        List<FragmentPart> parts = frag.getParts();
        try{
            parts.remove(partNum);
        } catch (IndexOutOfBoundsException e){
            return false;
        }
        frag.setParts(parts);
        return true;
    }
    
    @Override
    /**
     * deletes the selected segment when the user desires to delete a segment. 
     * 
     * @param frag  fragment reference corresponding to the current fragment
     */
    public void removeEmptyPart(Fragment frag) {
        List<FragmentPart> parts = frag.getParts();
        for (int i = 0; i < parts.size(); i++){
            if (parts.get(i).getType().equals("e")){
                deleteFragmentPart(frag, i);
                break;
            }
        }
    }

    // @Override
    // public void addAnnotation(Fragment frag, Annotation annotation){
    // Annotation annotations = frag.getAnnotations();
    // annotations.addAnnotation(annotation);
    // frag.setAnnotations(annotations);
    // }

    /**
     * Helps retrieve the fragment that a particular choice is linked to
     * 
     * @param frag is the fragment that the choice is contained in
     * @param partNum is the position of the choice in the fragmentParts
     * @return Fragment pointed to by choice.  If it is not a choice
     * at the displayOrderPos returns null.  
     */
    public int getLinkedToFragmentPosOfChoice(Fragment frag, int partNum) {
        List<FragmentPart> parts = frag.getParts();
        FragmentPart part;
        try{
            part = parts.get(partNum);
        } catch (IndexOutOfBoundsException e){
            return -1;
        }
        
        if (!(part.getType().equals("c")))
            return -1;
        else {
            //safe to cast now
            Choice choice = part.getChoice();
            return choice.getLinkedToFragmentPos();
        }
    }
    

    /**
     * changes the Id of a fragment at position id
     * 
     * @param f is the fragment we wish to change the id to
     * @param id is the id we wish to associciate with the fragment
     */
    public void setId(Fragment f, int id) {
        f.setId(id);
    }
}
