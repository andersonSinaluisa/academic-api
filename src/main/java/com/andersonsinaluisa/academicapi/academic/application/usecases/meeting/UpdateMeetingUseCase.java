package com.andersonsinaluisa.academicapi.academic.application.usecases.meeting;

import com.andersonsinaluisa.academicapi.academic.application.dtos.MeetingDto;
import com.andersonsinaluisa.academicapi.academic.application.mappers.MeetingMapper;
import com.andersonsinaluisa.academicapi.academic.domain.entities.meeting.Meeting;
import com.andersonsinaluisa.academicapi.academic.domain.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateMeetingUseCase {
    private final MeetingRepository repository;

    public Mono<MeetingDto> execute(Long id, MeetingDto dto) {
        Meeting meeting = MeetingMapper.fromDtoToDomain(dto);
        meeting.id = id;
        return repository.save(meeting).map(MeetingMapper::fromDomainToDto);
    }
}
