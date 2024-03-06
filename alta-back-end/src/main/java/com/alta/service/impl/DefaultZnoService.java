package com.alta.service.impl;

import com.alta.dto.FullZnoDto;
import com.alta.entity.Zno;
import com.alta.exception.ZnoException;
import com.alta.mapper.ZnoMapper;
import com.alta.repository.ZnoRepository;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultZnoService implements ZnoService {

    private final ZnoRepository znoRepository;
    private final ZnoMapper znoMapper;
    @Override
    public FullZnoDto findById(int id) {
        Optional<Zno> zno = znoRepository.findById(id);
        if (zno.isEmpty()) {
            throw new ZnoException(id);
        }
        return znoMapper.toDto(zno.get());
    }

    @Override
    public List<FullZnoDto> findAll() {
        return znoRepository.findAll().
                stream().map(znoMapper::toDto).toList();
    }
}
