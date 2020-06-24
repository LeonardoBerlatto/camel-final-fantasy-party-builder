package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.config;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.CamelContextWrapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartyBuilderConfiguration {

    @Bean
    public CamelContextWrapper camelContextWrapper(RouteBuilder... routes) throws Exception {
        return new CamelContextWrapper(routes);
    }
}
