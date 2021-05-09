package com.iag.person.service;

import com.iag.person.dto.AgifyResponse;
import com.iag.person.dto.Person;
import com.iag.person.exception.PersonApiErrorMapping;
import com.iag.person.exception.PersonApiException;
import com.iag.person.exception.PersonApiExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AgifyService {

    private static final String AGIFY_URL = "https://api.agify.io";
    private static final String AGIFY_QUERY_PARAM = "name";

    @Autowired
    PersonApiExceptionFactory personApiExceptionFactory;

    public Mono<AgifyResponse> findByName(String name) {
        WebClient webClient = WebClient.create(AGIFY_URL);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam(AGIFY_QUERY_PARAM, name).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(personApiExceptionFactory.createAPIException(clientResponse)))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(personApiExceptionFactory.createAPIException(clientResponse)))
                .bodyToMono(AgifyResponse.class);
    }


}
