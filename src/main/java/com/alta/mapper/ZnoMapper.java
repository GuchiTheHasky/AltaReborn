package com.alta.mapper;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZnoMapper {
    ZnoDto toZnoDto(Zno zno);

    Zno toZno(ZnoDto znoDto);
}
