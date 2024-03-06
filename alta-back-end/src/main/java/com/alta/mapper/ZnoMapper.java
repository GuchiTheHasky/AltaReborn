package com.alta.mapper;

import com.alta.dto.FullZnoDto;
import com.alta.entity.Zno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZnoMapper {

    FullZnoDto toDto(Zno zno);

}
