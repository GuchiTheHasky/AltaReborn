package com.alta.service;

import com.alta.dto.ZnoDto;

import java.util.List;

/**
 * Interface for managing the Zno data.
 * Provides functionality to return Zno objects.
 */

public interface ZnoService {

    /**
     * Returns a list of all Zno objects available.
     * @return A list of ZnoDto objects representing the available znos.
     */
    List<ZnoDto> findAll();
}
