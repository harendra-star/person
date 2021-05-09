package com.iag.person.service

import com.iag.person.dto.AgifyResponse
import com.iag.person.dto.Country
import com.iag.person.dto.GenderizeResponse
import com.iag.person.dto.NationalizeResponse
import com.iag.person.dto.Person
import com.iag.person.service.AgifyService
import com.iag.person.service.GenderizeService
import com.iag.person.service.NationalizeService
import com.iag.person.service.PersonOrchestrator
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class PersonOrchestratorTest extends Specification {

    @Subject
    PersonOrchestrator personOrchestrator;

    AgifyService agifyService;
    GenderizeService genderizeService
    NationalizeService nationalizeService

    def setup(){
        agifyService = Mock(AgifyService.class);
        genderizeService = Mock(GenderizeService.class);
        nationalizeService = Mock(NationalizeService.class);
        personOrchestrator = new PersonOrchestrator(agifyService, genderizeService, nationalizeService)
    }

    @Unroll
    def "Person orchestrator call"() {
        given:
        agifyService.findByName(_) >> Mono.just(new AgifyResponse("ABC", 89, 88))
        genderizeService.findByName(_) >> Mono.just(new GenderizeResponse("ABC", "F",89.01, 88))
        nationalizeService.findByName(_) >> Mono.just(new NationalizeResponse("ABC", Arrays.asList(new Country("AU", 89.78))))
        when:
        Mono<Person> person = personOrchestrator.findPerson("michael")
        then:
        StepVerifier.create(person).expectNext(new Person(9, "F", Arrays.asList("AU")));
    }

}