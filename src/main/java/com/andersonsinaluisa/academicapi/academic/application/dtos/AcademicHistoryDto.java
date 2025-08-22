package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AcademicHistoryDto {
    public Long studentId;
    public List<RecordDto> records;

    @Data
    @Builder
    public static class RecordDto {
        public String academicYearId;
        public String course;
        public Double finalAverage;
        public String status;
    }
}
