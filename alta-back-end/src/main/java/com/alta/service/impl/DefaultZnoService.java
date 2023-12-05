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

    @Override
    public ZnoDto save(ZnoDto znoDto) {
        Zno znoToSave = znoMapper.toZno(znoDto);
        znoRepository.save(znoToSave);
        return znoMapper.toZnoDto(znoToSave);
    }

    @Override
    public void delete(int id) {
        znoRepository.findById(id).ifPresent(zno -> znoRepository.deleteById(id));
    }

    @Override
    public ZnoDto update(int id, ZnoDto znoDto) {
        return znoRepository.findById(id)
                .map(znoRequired -> {
                    znoRequired.setName(znoDto.getName());
                    znoRequired.setYear(znoDto.getYear());
                    znoRepository.save(znoRequired);
                    return znoMapper.toZnoDto(znoRequired);
                })
                .orElseThrow(() -> new ZnoException(id));
    }
}

