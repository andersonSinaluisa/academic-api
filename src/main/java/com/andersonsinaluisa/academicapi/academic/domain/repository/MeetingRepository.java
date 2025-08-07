package com.andersonsinaluisa.academicapi.academic.domain.repository;

import com.andersonsinaluisa.academicapi.academic.domain.entities.meeting.Meeting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeetingRepository {
    Mono<Meeting> save(Meeting meeting);
    Flux<Meeting> findAll();
    Mono<Meeting> findById(Long id);
    Mono<Void> deleteById(Long id);
}

