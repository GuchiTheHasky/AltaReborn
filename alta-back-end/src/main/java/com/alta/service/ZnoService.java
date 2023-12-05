package com.alta.service;

import com.alta.entity.Zno;
import java.util.List;

/**
 * Interface for managing the Zno data.
 * Provides functionality to return, save, update, and delete Zno objects.
 */

public interface ZnoService {

    /**
     * Returns a list of all Zno objects.
     * @return A list containing all Zno objects.
     */
    List<Zno> findAll();

    /**
     * Saves a new Zno object.
     * @param zno : Zno object to be saved.
     * @return The saved Zno object.
     */
    Zno save(Zno zno);

    /**
     * Deletes a Zno object based on its unique identifier.
     * @param id : The unique identifier of the Zno object to be deleted.
     */
    void delete(int id);

    /**
     * Updates an existing Zno object.
     * @param id : The unique identifier of the Zno object to be updated.
     * @param zno : Zno object with updated data.
     * @return The updated Zno object.
     */
    Zno update(int id, Zno zno);
}
