package com.alta.service;

import com.alta.dto.TaskDto;
import com.alta.dto.ZnoDto;

import java.util.List;

public interface ZnoService {
    List<ZnoDto> findAll();

    List<TaskDto> findTasksByZnoId(int id);
}
