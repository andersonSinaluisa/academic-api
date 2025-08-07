package com.andersonsinaluisa.academicapi.academic.application.usecases.attendance;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.AttendanceMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.attendance.Attendance;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateAttendanceUseCase {
    private final AttendanceRepository repository;

    public Mono<AttendanceDto> execute(Long id, AttendanceDto dto) {
        Attendance attendance = AttendanceMapper.fromDtoToDomain(dto);
        attendance.id = id;
        return repository.save(attendance)
                .map(AttendanceMapper::fromDomainToDto);
    }
}
