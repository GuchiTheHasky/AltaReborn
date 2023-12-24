package com.alta.service.impl;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import com.alta.exception.ZnoException;
import com.alta.mapper.ZnoMapper;
import com.alta.repository.ZnoRepository;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultZnoService implements ZnoService {
    private final ZnoRepository znoRepository;
    private final ZnoMapper znoMapper;

    @Override
    public List<ZnoDto> findAll() {
        return znoRepository.findAll().stream().map(znoMapper::toZnoDto).collect(Collectors.toList());
    }
}
