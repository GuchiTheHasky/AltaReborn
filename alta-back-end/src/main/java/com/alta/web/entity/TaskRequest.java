package com.alta.web.entity;

import java.util.List;

public record TaskRequest(List<Integer> studentsIds, List<Integer> tasksIds) {}
