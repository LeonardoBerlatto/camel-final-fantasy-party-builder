package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes.FinalFantasyAPIRouter.FINAL_FANTASY_API_ROUTE_URI;
import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes.PartyBuilderAPIRouter.PARTY_BUILDER_SERVICE_ROUTE_URI;

@Component
public class PartyBuilderRouter extends RouteBuilder {

    public static final String PARTY_BUILDER_ROUTE_URI = "rabbitmq:party-builder?autoDelete=false";

    public static final String PARTY_ID = "partyId";

    @Override
    public void configure() {
        // reads XML from RabbitMQ
        from(PARTY_BUILDER_ROUTE_URI).
                routeId("main-route").
                log("${body}").
                setHeader(PARTY_ID, xpath("/party/partyId/text()")).
                split().xpath("/party/members/name"). // splits for every party member to add to the party
                to(FINAL_FANTASY_API_ROUTE_URI).
                to(PARTY_BUILDER_SERVICE_ROUTE_URI);
    }
}
