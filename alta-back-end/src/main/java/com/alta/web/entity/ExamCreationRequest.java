package com.alta.web.entity;

import java.util.List;

public record ExamCreationRequest(String title, List<Integer> studentsIds, List<Integer> tasksIds) {
}
