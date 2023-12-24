package com.alta.mapper;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZnoMapper {
    Zno toZno(ZnoDto znoDto);
    ZnoDto toZnoDto(Zno zno);

}
