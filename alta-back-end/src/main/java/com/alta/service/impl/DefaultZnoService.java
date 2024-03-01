package com.alta.service.impl;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import com.alta.exception.ZnoException;
import com.alta.mapper.ZnoMapper;
import com.alta.repository.ZnoRepository;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultZnoService implements ZnoService {
    private final ZnoRepository znoRepository;
    private final ZnoMapper znoMapper;

    @Override
    public List<ZnoDto> findAll() {
        return znoRepository.findAll().stream()
                .map(znoMapper::toZnoDto)
                .sorted(Comparator.comparing(ZnoDto::getName))
                .toList();
    }

    @Override
    public List<ZnoDto> findAllZnosPageByPage(PageRequest pageRequest) {
        Optional<Page<Zno>> optionalZnosPage = Optional.of(znoRepository.findAll(pageRequest));

        return optionalZnosPage.map(page ->
                page.getContent().stream()
                        .map(znoMapper::toZnoDto)
                        .sorted(Comparator.comparing(ZnoDto::getName))
                        .toList()
        ).orElse(Collections.emptyList());
    }

    @Override
    public ZnoDto findById(int id) {
        return znoRepository.findById(id)
                .map(znoMapper::toZnoDto)
                .orElseThrow(() -> new ZnoException(id));
    }
}
