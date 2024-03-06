package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import com.alta.exception.ZnoException;
import com.alta.mapper.TaskMapper;
import com.alta.mapper.ZnoMapper;
import com.alta.repository.ZnoRepository;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultZnoService implements ZnoService {

    private final ZnoRepository znoRepository;
    private final ZnoMapper znoMapper;
    private final TaskMapper taskMapper;

    @Override
    public List<ZnoDto> findAll() {
        return znoRepository.findAll().
                stream().map(znoMapper::toZnoDto).toList();
    }

    @Override
    public List<TaskDto> findTasksByZnoId(int id) {
        return znoRepository.findById(id)
                .map(Zno::getTasks)
                .orElseThrow(() -> new ZnoException(id))
                .stream()
                .map(taskMapper::toTaskDto)
                .toList();
    }

}
