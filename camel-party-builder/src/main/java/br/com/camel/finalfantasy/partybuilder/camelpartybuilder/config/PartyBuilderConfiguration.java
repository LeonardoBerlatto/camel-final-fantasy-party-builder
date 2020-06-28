package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.config;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.CamelContextWrapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PartyBuilderConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .build();
    }

    @Bean
    public CamelContextWrapper camelContextWrapper(RouteBuilder... routes) throws Exception {
        return new CamelContextWrapper(routes);
    }
}
