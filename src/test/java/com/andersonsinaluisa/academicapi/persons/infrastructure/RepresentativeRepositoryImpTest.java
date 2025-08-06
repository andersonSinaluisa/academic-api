package com.andersonsinaluisa.academicapi.persons.infrastructure;

import com.andersonsinaluisa.academicapi.persons.domain.entities.Representative;
import com.andersonsinaluisa.academicapi.persons.domain.entities.TypePerson;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.BirthDate;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.FullName;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.Identification;
import com.andersonsinaluisa.academicapi.persons.domain.valueObjects.PhoneNumber;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.RepresentativePgRepository;
import com.andersonsinaluisa.academicapi.persons.infrastructure.database.repository.custom.RepresentativeCustomRepository;
import com.andersonsinaluisa.academicapi.shared.domain.FilterCriteria;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.ZoneId;

@DataR2dbcTest
@Import({RepresentativeRepositoryImpl.class, RepresentativeCustomRepository.class})

@TestPropertySource(locations = "classpath:application-test.properties")
public class RepresentativeRepositoryImpTest {
    Faker faker = new Faker();

    @Autowired
    private RepresentativePgRepository representativePgRepository;

    @Autowired
    private RepresentativeRepositoryImpl representativeRepository;


    public Representative generate(){
        return Representative.builder()
                .fullName(new FullName(faker.name().firstName(), faker.name().lastName()))
                .gender(faker.bool().bool() ? "Masculino" : "Femenino")
                .image(faker.internet().image())
                .phone(new PhoneNumber(faker.numerify("#########")))
                .identification(new Identification(faker.numerify("#########")))
                .address(faker.address().fullAddress())
                .nacionality(faker.address().country())
                .uuidUser(faker.numerify("abc-sss-###########"))
                .birthDate(new BirthDate(faker.timeAndDate().birthday(18,50)))
                .typePerson(TypePerson.REPRESENTATIVE)
                .build();
    }
    @Test
    void createRepresentative_ValidRepresentative() {


        Representative representative = generate();

        StepVerifier.create(representativeRepository
                        .create(representative))
                .expectNextMatches(rep ->
                        rep.identification.getValue().equals(representative.identification.getValue()))
                .verifyComplete();
    }

    @Test
    void updateRepresentative_success() {
        Representative rep = generate();

        StepVerifier.create(
                representativeRepository.create(rep)
                        .flatMap(saved -> {
                    saved.address = "Dirección actualizada";
                    return representativeRepository.update(saved);
                })
                )
                .expectNextMatches(updated -> updated.address.equals("Dirección actualizada"))
                .verifyComplete();
    }
    @Test
    void deleteRepresentative_success() {
        Representative rep = generate();

        Mono<Void> flow = representativeRepository.create(rep)
                .flatMap(saved -> representativeRepository.delete(saved.id));

        StepVerifier.create(flow)
                .verifyComplete();
    }

    @Test
    void getByStudent_returnFluxOfRepresentatives() {
        Long studentId = 1L; // debe existir un estudiante de prueba
        StepVerifier.create(representativeRepository.getByStudent(studentId))
                .expectNextCount(0) // ajusta según setup
                .verifyComplete();
    }

    @Test
    void allRepresentative_returnPagedResult() {
        FilterCriteria filters = new FilterCriteria(); // sin filtros para ahora
        Pageable pageable = PageRequest.of(0, 10);

        StepVerifier.create(representativeRepository.all(pageable, filters))
                .expectNextMatches(page -> page.getContent().size() <= 10)
                .verifyComplete();
    }
}

