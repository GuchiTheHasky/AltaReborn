package com.alta.mapper;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    @Mapping(target = "subtopics", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Topic toTopic(TopicDto topicDto);


    TopicDto toTopicDto(Topic topic);
}
