package com.andersonsinaluisa.academicapi.academic.api.controllers;

import com.andersonsinaluisa.academicapi.academic.application.dtos.MeetingDto;
import com.andersonsinaluisa.academicapi.academic.application.usecases.meeting.CreateMeetingRecordUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.meeting.ListMeetingUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.meeting.UpdateMeetingUseCase;
import com.andersonsinaluisa.academicapi.academic.application.usecases.meeting.DeleteMeetingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final CreateMeetingRecordUseCase createMeetingRecordUseCase;
    private final ListMeetingUseCase listMeetingUseCase;
    private final UpdateMeetingUseCase updateMeetingUseCase;
    private final DeleteMeetingUseCase deleteMeetingUseCase;

    @PostMapping
    public Mono<MeetingDto> create(@RequestBody MeetingDto dto) {
        return createMeetingRecordUseCase.execute(dto);
    }

    @GetMapping
    public Flux<MeetingDto> list() {
        return listMeetingUseCase.execute();
    }

    @PutMapping("/{id}")
    public Mono<MeetingDto> update(@PathVariable Long id, @RequestBody MeetingDto dto) {
        return updateMeetingUseCase.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteMeetingUseCase.execute(id);
    }
}
