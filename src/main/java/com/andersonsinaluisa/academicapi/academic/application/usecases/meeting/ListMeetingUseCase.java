package com.andersonsinaluisa.academicapi.academic.application.usecases.meeting;

import com.andersonsinaluisa.academicapi.academic.application.dtos.MeetingDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.MeetingMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ListMeetingUseCase {
    private final MeetingRepository repository;

    public Flux<MeetingDto> execute() {
        return repository.findAll().map(MeetingMapper::fromDomainToDto);
    }
}
