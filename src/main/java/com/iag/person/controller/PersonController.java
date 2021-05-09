package com.iag.person.controller;

import com.iag.person.dto.Person;
import com.iag.person.service.PersonOrchestrator;
import com.iag.person.validator.NameConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("person")
@Validated
public class PersonController {

    @Autowired
    PersonOrchestrator personService;

    @GetMapping()
    public Mono<ResponseEntity<Person>> getUserById(@RequestParam("name") String name){
        Mono<Person> person = personService.findPerson(name);
        return person.map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
