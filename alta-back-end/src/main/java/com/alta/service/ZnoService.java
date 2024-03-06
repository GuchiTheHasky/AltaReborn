package com.alta.service;

import com.alta.dto.FullZnoDto;

import java.util.List;

public interface ZnoService {
    FullZnoDto findById(int id);

    List<FullZnoDto> findAll();
}
