package com.andersonsinaluisa.academicapi.academic.application.usecases.meeting;

import com.andersonsinaluisa.academicapi.academic.domain.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteMeetingUseCase {
    private final MeetingRepository repository;

    public Mono<Void> execute(Long id) {
        return repository.deleteById(id);
    }
}
