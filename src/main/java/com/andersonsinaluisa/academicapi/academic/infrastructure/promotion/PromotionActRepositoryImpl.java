package com.andersonsinaluisa.academicapi.academic.infrastructure.promotion;

import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.PromotionAct;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionActRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PromotionActRepositoryImpl implements PromotionActRepository {

    private final Map<Long, PromotionAct> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Mono<PromotionAct> save(PromotionAct act) {
        if (act.id == null) {
            act.id = sequence.getAndIncrement();
            act.generatedAt = LocalDate.now();
        }
        store.put(act.id, act);
        return Mono.just(act);
    }

    @Override
    public Mono<PromotionAct> findById(Long id) {
        return Mono.justOrEmpty(store.get(id));
    }

    @Override
    public Flux<PromotionAct> findByCourseIdAndAcademicYearId(String courseId, String academicYearId) {
        return Flux.fromStream(store.values().stream()
                .filter(a -> (courseId == null || courseId.equals(a.courseId))
                        && (academicYearId == null || academicYearId.equals(a.academicYearId))));
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        store.remove(id);
        return Mono.empty();
    }
}
