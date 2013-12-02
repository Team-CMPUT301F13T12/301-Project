
package ualberta.g12.adventurecreator.controllers;

import ualberta.g12.adventurecreator.data.Choice;
import ualberta.g12.adventurecreator.data.Fragment;
import ualberta.g12.adventurecreator.data.FragmentPart;

import java.util.List;

/**
 * A controller class that is used to modify a {@link Fragment}. All changes to
 * fragments should be done via this controller or another implementation of
 * {@link FController}.
 */
public class FragmentController implements FController {

    private static final String TAG = "FragmentController";

    /**
     * Changes the title of the given fragment to the given title. There are no
     * limits on the length of this title.
     * 
     * @param frag the fragment whose title we are changing
     * @param newTitle the new title of the fragment
     */
    @Override
    public void setTitle(Fragment frag, String newTitle) {
        frag.setTitle(newTitle);
    }

    public FragmentPart addNewFragmentPart(Fragment frag, String type, int pos) {
        FragmentPart newPart = new FragmentPart(type);
        List<FragmentPart> parts = frag.getParts();
        try {
            parts.add(pos, newPart);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        frag.setParts(parts);
        return newPart;
    }

    public void setFragmentPartData(Fragment frag, FragmentPart part, String data) {
        part.setData(data);
        frag.setParts(frag.getParts());
    }

    public void setFragmentPartChoice(Fragment frag, FragmentPart part, Choice choice) {
        part.setChoice(choice);
        frag.setParts(frag.getParts());
    }

    public void setFragmentPartPicSize(Fragment frag, FragmentPart part, int picSize) {
        part.setPicSize(picSize);
        frag.setParts(frag.getParts());
    }

    /**
     * Tries to remove a fragment part at the given partNum. If there is no part
     * at this partNum, we will return false and perform no deletions. If there
     * is a fragment part at this partNum we will delete it return true.
     * 
     * @param frag the fragment to delete the choice from
     * @param partNum the position of the fragment part to delete
     * @return true if the choice was deleted, otherwise false
     */
    @Override
    public boolean deleteFragmentPart(Fragment frag, int partNum) {
        List<FragmentPart> parts = frag.getParts();
        try {
            parts.remove(partNum);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        frag.setParts(parts);
        return true;
    }

    /**
     * Removes the first occurrence of an empty part in the Fragments list of
     * segments. If there are no empty parts, nothing is deleted.
     * 
     * @param frag the fragment to remove the empty part from.
     */
    @Override
    public void removeEmptyPart(Fragment frag) {
        List<FragmentPart> parts = frag.getParts();
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getType().equals("e")) {
                deleteFragmentPart(frag, i);
                break;
            }
        }
    }

    /**
     * Retrieve the fragment that a particular choice is linked to
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
}
