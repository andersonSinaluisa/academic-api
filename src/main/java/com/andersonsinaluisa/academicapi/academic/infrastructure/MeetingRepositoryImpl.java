package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.meeting.Meeting;
import com.andersonsinaluisa.academicapi.academic.domain.repository.MeetingRepository;
import com.andersonsinaluisa.academicapi.academic.domain.valueObjects.MeetingDate;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.meeting.MeetingTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.MeetingPgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MeetingRepositoryImpl implements MeetingRepository {

    private final MeetingPgRepository repository;

    @Override
    public Mono<Meeting> save(Meeting meeting) {
        return repository.save(toTable(meeting)).map(this::toDomain);
    }

    @Override
    public Flux<Meeting> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    private MeetingTable toTable(Meeting m) {
        return MeetingTable.builder()
                .id(m.id)
                .courseId(m.courseId)
                .academicYearId(m.academicYearId)
                .meetingDate(m.meetingDate.getValue().toLocalDate())
                .location(m.location)
                .meetingType(m.meetingType)
                .createdBy(m.createdBy)
                .build();
    }

    private Meeting toDomain(MeetingTable t) {
        return Meeting.builder()
                .id(t.id)
                .courseId(t.courseId)
                .academicYearId(t.academicYearId)
                .meetingDate(new MeetingDate(t.meetingDate.atStartOfDay()))
                .location(t.location)
                .meetingType(t.meetingType)
                .createdBy(t.createdBy)
                .build();
    }
}

