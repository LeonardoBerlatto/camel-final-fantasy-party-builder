package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PartyBuilderRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("file:input?noop=true&include=party_.*\\.xml").
                to("file:saida");
    }
}
