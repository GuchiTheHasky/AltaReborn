package com.alta.service;

import com.alta.dto.ZnoDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ZnoService {
    List<ZnoDto> findAll();

    List<ZnoDto> findAllZnosPageByPage(PageRequest pageRequest);

    ZnoDto findById(int id);
}
