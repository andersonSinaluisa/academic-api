package com.andersonsinaluisa.academicapi.academic.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OfficialRecordDto {
    public Long studentId;
    public String fullName;
    public List<RecordDto> records;

    @Data
    @Builder
    public static class RecordDto {
        public String academicYearId;
        public String course;
        public Double averageScore;
        public String status;
    }
}

