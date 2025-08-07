package com.andersonsinaluisa.academicapi.academic.application.usecases.attendance;

import com.andersonsinaluisa.academicapi.academic.application.dtos.AttendanceDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.AttendanceMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ListAttendanceUseCase {
    private final AttendanceRepository repository;

    public Flux<AttendanceDto> execute() {
        return repository.findAll().map(AttendanceMapper::fromDomainToDto);
    }
}
