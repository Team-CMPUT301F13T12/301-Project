
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

    /**
     * Changes the title of the given fragment to the given title. There are no
     * limits on the length of this title.
     * 
     * @param fragment the fragment whose title we are changing
     * @param newTitle the new title of the fragment, must not be null
     */
    @Override
    public void setTitle(Fragment fragment, String newTitle) {
        fragment.setTitle(newTitle);
    }

    /**
     * Adds a new Fragment part of the given type to the FragmentPart list of
     * the fragment.
     * 
     * @param fragment to add the FragmentPart to
     * @param type is the type of FragmentPart to add, should be "t" (text), "i"
     *            (illustration), "c" (choice), or "e" (empty)
     * @param pos is the position to insert the FragmentPart in the FragmentPart
     *            list of fragment
     * @return the newly created FragmentPart
     * @see FragmentPart
     */
    @Override
    public FragmentPart addNewFragmentPart(Fragment fragment, String type, int pos) {
        FragmentPart newPart = new FragmentPart(type);
        List<FragmentPart> parts = fragment.getParts();
        try {
            parts.add(pos, newPart);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        fragment.setParts(parts);
        return newPart;
    }

    /**
     * Sets the data field of a FragmentPart in the FragmentPart list of
     * fragment. Should only be used on "t" (text) or "i" (illustration) type
     * FragmentParts.
     * 
     * @param fragment Fragment that owns the given FragmentPart, part
     * @param part the FragmentPart to modify
     * @param data the new data to be set for part, must not be null
     * @see FragmentPart
     */
    @Override
    public void setFragmentPartData(Fragment fragment, FragmentPart part, String data) {
        part.setData(data);
        fragment.setParts(fragment.getParts());
    }

    /**
     * Sets the choice field of a FragmentPart in the FragmentPart list of
     * fragment. Should only be used on "c" (choice) type FragmentParts.
     * 
     * @param fragment Fragment that owns the given FragmentPart, part
     * @param part the FragmentPart to modify
     * @param choice the new choice to be set for part, must not be null
     * @see FragmentPart
     */
    @Override
    public void setFragmentPartChoice(Fragment fragment, FragmentPart part, Choice choice) {
        part.setChoice(choice);
        fragment.setParts(fragment.getParts());
    }

    /**
     * Sets the picSize field of a FragmentPart in the FragmentPart list of
     * fragment. Should only be used on "i" (illustration) type FragmentParts.
     * 
     * @param fragment Fragment that owns the given FragmentPart, part
     * @param part the FragmentPart to modify
     * @param picSize the new picSize to be set for part, must not be null
     * @see FragmentPart
     */
    @Override
    public void setFragmentPartPicSize(Fragment fragment, FragmentPart part, int picSize) {
        part.setPicSize(picSize);
        fragment.setParts(fragment.getParts());
    }

    /**
     * Tries to remove a fragment part at the given partNum. If there is no part
     * at this partNum, we will return false and perform no deletions. If there
     * is a fragment part at this partNum we will delete it return true.
     * 
     * @param fragment the fragment to delete the choice from
     * @param partNum the position of the fragment part to delete
     * @return true if the choice was deleted, otherwise false
     */
    @Override
    public boolean deleteFragmentPart(Fragment fragment, int partNum) {
        List<FragmentPart> parts = fragment.getParts();
        try {
            parts.remove(partNum);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        fragment.setParts(parts);
        return true;
    }

    /**
     * Removes the first occurrence of an empty FragmentPart, type = "e", in the
     * FragmentPart list of fragment. If there are no empty parts nothing is
     * deleted.
     * 
     * @param fragment the fragment to remove the empty part from.
     * @see FragmentPart
     */
    @Override
    public void removeEmptyPart(Fragment fragment) {
        List<FragmentPart> parts = fragment.getParts();
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).getType().equals("e")) {
                deleteFragmentPart(fragment, i);
                break;
            }
        }
    }
}
