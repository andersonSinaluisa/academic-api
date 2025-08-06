package com.andersonsinaluisa.academicapi.academic.application.usecases.attendance;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.AttendanceMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RecordAttendanceUseCase {

    private final AttendanceRepository repository;

    public Mono<AttendanceDto> execute(AttendanceDto dto) {
        return repository.save(AttendanceMapper.fromDtoToDomain(dto))
                .map(AttendanceMapper::fromDomainToDto);
    }
}

