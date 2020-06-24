package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.processor.MemberRequestProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class PartyBuilderAPIRouter extends RouteBuilder {

    public static final String PARTY_BUILDER_SERVICE_ROUTE_URI = "direct:party-builder-service-route";

    public static final String PARTY_BUILDER_SERVICE_URI = "http://localhost:8081/api/members/party";

    @Override
    public void configure() throws Exception {
        from(PARTY_BUILDER_SERVICE_ROUTE_URI).
                routeId("party-builder-service-route").
                log("Adding characters for party with id ${exchangeProperty.partyId}").
                process(new MemberRequestProcessor()).
                log("${body}").
                setHeader(Exchange.HTTP_METHOD, HttpMethods.POST).
                setHeader(Exchange.HTTP_PATH, simple("${exchangeProperty.partyId}")).
                removeHeader(Exchange.HTTP_QUERY).
                to(PARTY_BUILDER_SERVICE_URI); // sends a http request to local party builder API to add party member
    }
}
