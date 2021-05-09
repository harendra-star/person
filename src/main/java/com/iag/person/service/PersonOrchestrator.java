package com.iag.person.service;

import com.iag.person.dto.AgifyResponse;
import com.iag.person.dto.GenderizeResponse;
import com.iag.person.dto.NationalizeResponse;
import com.iag.person.dto.Person;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonOrchestrator {
    @Autowired
    AgifyService agifyService;
    @Autowired
    GenderizeService genderizeService;
    @Autowired
    NationalizeService nationalizeService;

    public Mono<Person> findPerson(String name) {
        Mono<AgifyResponse> agifyResponseMono = agifyService.findByName(name);
        Mono<GenderizeResponse> genderizeResponseMono = genderizeService.findByName(name);
        Mono<NationalizeResponse> nationalizeResponseMono = nationalizeService.findByName(name);

        return Mono.zip(agifyResponseMono, genderizeResponseMono, nationalizeResponseMono).map(data ->
                new Person(data.getT1().getAge(), data.getT2().getGender(),
                        data.getT3().getCountry().stream()
                                .map(country -> country.getCountryId()).collect(Collectors.toList()))
        );
    }
}