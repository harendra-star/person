package com.iag.person.service;

import com.iag.person.dto.AgifyResponse;
import com.iag.person.dto.GenderizeResponse;
import com.iag.person.dto.Person;
import com.iag.person.exception.PersonApiExceptionFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GenderizeService {
    private static final String GENDERIZE_URL = "https://api.genderize.io";
    private static final String GENDERIZE_QUERY_PARAM = "name";
    @Autowired
    PersonApiExceptionFactory personApiExceptionFactory;

    public Mono<GenderizeResponse> findByName(String name) {
        WebClient webClient = WebClient.create(GENDERIZE_URL);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam(GENDERIZE_QUERY_PARAM, name).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(personApiExceptionFactory.createAPIException(clientResponse)))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(personApiExceptionFactory.createAPIException(clientResponse)))
                .bodyToMono(GenderizeResponse.class);
    }
}
