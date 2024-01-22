package com.alta.dto;

import com.alta.entity.Task;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {
    private int id;
    private String title;
    private List<Task> tasks;
}
