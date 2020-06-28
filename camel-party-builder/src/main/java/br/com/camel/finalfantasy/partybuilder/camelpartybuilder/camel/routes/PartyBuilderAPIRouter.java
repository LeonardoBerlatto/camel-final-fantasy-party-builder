package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.processor.FinalFantasyCharacterProcessor;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.PartyBuilderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartyBuilderAPIRouter extends RouteBuilder {

    public static final String PARTY_BUILDER_SERVICE_ROUTE_URI = "direct:party-builder-service-route";

    private final PartyBuilderRepository partyBuilderRepository;

    @Override
    public void configure() throws Exception {
        from(PARTY_BUILDER_SERVICE_ROUTE_URI).
                routeId("party-builder-service-route").
                log("Adding characters for party with id ${exchangeProperty.partyId}").
                process(new FinalFantasyCharacterProcessor()).
                log("${body}").
                bean(partyBuilderRepository, "addCharacterToParty(${body}, ${exchangeProperty.partyId})"); // sends a http request to local party builder API to add party member
    }
}
