package com.andersonsinaluisa.academicapi.academic.application.usecases.meeting;

import com.andersonsinaluisa.academicapi.academic.application.dtos.MeetingDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.MeetingMapper;
import com.andersonsinaluisa.academicapi.academic.domain.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateMeetingRecordUseCase {

    private final MeetingRepository repository;

    public Mono<MeetingDto> execute(MeetingDto dto) {
        return repository.save(MeetingMapper.fromDtoToDomain(dto))
                .map(MeetingMapper::fromDomainToDto);
    }
}

