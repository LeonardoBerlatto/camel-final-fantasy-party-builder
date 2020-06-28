package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class HttpEntityUtils {

    public static HttpEntity createJSONRequestEntity(Object signedRequest) {
        return new HttpEntity(signedRequest, createHeadersJSON());
    }

    public static HttpHeaders createHeadersJSON() {
        return new HttpHeaders() {{
            setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}
