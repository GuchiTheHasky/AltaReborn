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
    private final ZnoRepository ZNO_REPOSITORY;
    private final ZnoMapper ZNO_MAPPER;

    @Override
    public List<ZnoDto> findAll() {
        return ZNO_REPOSITORY.findAll().stream().map(ZNO_MAPPER::toZnoDto).collect(Collectors.toList());
    }

    @Override
    public ZnoDto save(ZnoDto znoDto) {
        Zno newZno = ZNO_MAPPER.toZno(znoDto);
        return ZNO_MAPPER.toZnoDto(ZNO_REPOSITORY.save(newZno));
    }

    @Override
    public void delete(int id) {
        ZNO_REPOSITORY.findById(id).ifPresent(zno -> ZNO_REPOSITORY.deleteById(id));
    }

    @Override
    public ZnoDto update(int id, ZnoDto znoDto) {
        return ZNO_REPOSITORY.findById(id)
                .map(znoRequired -> {
                    znoRequired.setName(znoDto.getName());
                    znoRequired.setYear(znoDto.getYear());
                    return ZNO_MAPPER.toZnoDto(ZNO_REPOSITORY.save(znoRequired));
                })
                .orElseThrow(() -> new ZnoException(id));
    }
}

