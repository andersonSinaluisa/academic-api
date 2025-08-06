package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.MeetingDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.meeting.CreateMeetingRecordUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final CreateMeetingRecordUseCase createMeetingRecordUseCase;

    @PostMapping
    public Mono<MeetingDto> create(@RequestBody MeetingDto dto) {
        return createMeetingRecordUseCase.execute(dto);
    }
}
