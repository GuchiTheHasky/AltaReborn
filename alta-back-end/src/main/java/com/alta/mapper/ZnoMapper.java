package com.alta.mapper;

import com.alta.dto.ZnoDto;
import com.alta.entity.Zno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ZnoMapper {
    ZnoDto toZnoDto(Zno zno);
}
