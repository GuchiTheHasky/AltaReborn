package com.alta.web.entity;

import java.util.List;

public record ExamRequest(String title, List<Integer> studentsIds, List<Integer> tasksIds) {
}
