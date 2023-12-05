package com.alta.service;

import com.alta.dto.ZnoDto;

import java.util.List;

/**
 * Interface for managing the Zno data.
 * Provides functionality to return, save, update, and delete Zno objects.
 */

public interface ZnoService {

    /**
     * Returns a list of all Zno objects.
     *
     * @return A list containing all Zno objects.
     */
    List<ZnoDto> findAll();

    /**
     * Saves a new Zno object.
     *
     * @param znoDto : Zno object to be saved.
     * @return The saved Zno object.
     */
    ZnoDto save(ZnoDto znoDto);

    /**
     * Deletes a Zno object based on its unique identifier.
     *
     * @param id : The unique identifier of the Zno object to be deleted.
     */
    void delete(int id);

    /**
     * Updates an existing Zno object.
     *
     * @param id     : The unique identifier of the Zno object to be updated.
     * @param znoDto : Zno object with updated data.
     * @return The updated Zno object.
     */
    ZnoDto update(int id, ZnoDto znoDto);
}
