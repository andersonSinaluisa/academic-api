package com.andersonsinaluisa.academicapi.academic.infrastructure.promotion;

import com.andersonsinaluisa.academicapi.academic.domain.entities.promotion.Promotion;
import com.andersonsinaluisa.academicapi.academic.domain.repository.PromotionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PromotionRepositoryImpl implements PromotionRepository {

    private final Map<Long, Promotion> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Mono<Promotion> save(Promotion promotion) {
        if (promotion.id == null) {
            promotion.id = sequence.getAndIncrement();
        }
        store.put(promotion.id, promotion);
        return Mono.just(promotion);
    }

    @Override
    public Flux<Promotion> findByStudentId(Long studentId) {
        return Flux.fromStream(store.values().stream()
                .filter(p -> studentId == null || studentId.equals(p.studentId)));
    }
}
