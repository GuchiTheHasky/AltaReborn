package com.alta.service;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ZnoService {
    List<ZnoDto> findAll();

    List<ZnoDto> findAllZnosPageByPage(PageRequest pageRequest);

    Zno findById(int id);
}
