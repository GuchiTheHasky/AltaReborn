package com.alta.web.controller.request;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PdfRequest {
    private String fileName;
    private int studentId;
    private List<TaskRequest> filteredTaskDto;
}
