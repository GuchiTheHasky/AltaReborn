package com.alta.service;

import com.alta.dto.ZnoDto;

import java.util.List;

/**
 * Interface for managing the Zno data.
 * Provides functionality to return, save, update, and delete Zno objects.
 */

public interface ZnoService {

    /**
     * Returns a list of all Zno objects available.
     *
     * @return A list of ZnoDto objects representing the available znos.
     */
    List<ZnoDto> findAll();

    /**
     * Saves a new Zno.
     *
     * @param znoDto : An object containing information about the zno to be saved.
     * @return An object representing the newly saved zno.
     */
    ZnoDto save(ZnoDto znoDto);

    /**
     * Deletes a Zno object based on its unique identifier.
     *
     * @param id : The unique identifier of the Zno object to be deleted.
     */
    void delete(int id);

    /**
     * Updates an existing Zno object with the provided information.
     *
     * @param id : The unique identifier of the Zno object to be updated.
     * @param znoDto : An object containing the updated information for the zno.
     * @return An object representing the updated zno.
     */
    ZnoDto update(int id, ZnoDto znoDto);
}
