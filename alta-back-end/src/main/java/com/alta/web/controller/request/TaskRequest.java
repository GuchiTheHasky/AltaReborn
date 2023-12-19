package com.alta.web.controller.request;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class TaskRequest {
    @NonNull
    private List<Integer> taskIds;
    @NonNull
    private Integer topicId;
}
