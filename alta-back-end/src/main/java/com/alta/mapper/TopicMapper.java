package com.alta.mapper;

import com.alta.dto.TopicDto;
import com.alta.entity.Topic;
import org.mapstruct.Mapper;

@Mapper
public interface TopicMapper {

    TopicDto toTopicDto(Topic topic);

    Topic toTopic(TopicDto topicDto);

}
