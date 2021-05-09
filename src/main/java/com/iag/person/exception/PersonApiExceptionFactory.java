package com.iag.person.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.Optional;

@Component
public class PersonApiExceptionFactory {

    public PersonApiException createAPIException(ClientResponse clientResponse) {
        PersonApiErrorMapping personApiError = PersonApiErrorMapping.getByStatus(clientResponse.statusCode());
        return Optional.ofNullable(personApiError)
                .map(a -> new PersonApiException(a.getStatus(), a.getMessage()))
                .orElse(new PersonApiException(PersonApiErrorMapping.API_INTERNAL_SERVER_ERROR.getStatus(),
                        PersonApiErrorMapping.API_INTERNAL_SERVER_ERROR.getMessage()));
    }
}
