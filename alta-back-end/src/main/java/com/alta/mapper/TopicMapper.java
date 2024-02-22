package com.alta.mapper;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicDto toTopicDto(Topic topic);
}
