package com.andersonsinaluisa.academicapi.academic.application.usecases.attendance;

import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteAttendanceUseCase {
    private final AttendanceRepository repository;

    public Mono<Void> execute(Long id) {
        return repository.deleteById(id);
    }
}
