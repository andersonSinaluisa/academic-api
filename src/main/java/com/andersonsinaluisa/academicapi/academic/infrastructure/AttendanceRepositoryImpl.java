package com.andersonsinaluisa.academicapi.academic.infrastructure;

import com.andersonsinaluisa.academicapi.academic.domain.entities.attendance.Attendance;
import com.andersonsinaluisa.academicapi.academic.domain.repository.AttendanceRepository;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.entities.attendance.AttendanceTable;
import com.andersonsinaluisa.academicapi.academic.infrastructure.database.repository.jpa.AttendancePgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepository {

    private final AttendancePgRepository repository;

    @Override
    public Mono<Attendance> save(Attendance attendance) {
        return repository.save(toTable(attendance)).map(this::toDomain);
    }

    @Override
    public Flux<Attendance> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    @Override
    public Mono<Attendance> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    private AttendanceTable toTable(Attendance a) {
        return AttendanceTable.builder()
                .id(a.id)
                .subjectId(a.subjectId)
                .studentId(a.studentId)
                .date(a.date)
                .status(a.status)
                .observation(a.observation)
                .build();
    }

    private Attendance toDomain(AttendanceTable t) {
        return Attendance.builder()
                .id(t.id)
                .subjectId(t.subjectId)
                .studentId(t.studentId)
                .date(t.date)
                .status(t.status)
                .observation(t.observation)
                .build();
    }
}

