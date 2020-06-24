package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class PartyBuilderRoute extends RouteBuilder {

    public static final String BRACkETS_REGEX = "[\\[\\]']+";

    @Override
    public void configure() {
        // read all party_**.xml files from input folder
        from("file:input?noop=true&include=party_.*\\.xml").
                routeId("main-route").
                log("${body}").
                split().xpath("/parties/party"). // splits for every party
                log("${body}").
                setProperty("partyId", xpath("/party/partyId/text()")).
                split().xpath("/party/members/name"). // splits for every party member to add to the party
                to("direct:ff-api-route").
                to("direct:party-builder-service-route");

        from("direct:ff-api-route").
                routeId("ff-api-route").
                log("${body}").
                setProperty("name", xpath("/name/text()")).
                log("Querying data from character: ${exchangeProperty.name}").
                setHeader(Exchange.HTTP_METHOD, HttpMethods.GET).
                setHeader(Exchange.HTTP_QUERY, simple("name=${exchangeProperty.name}")).
                to("https://www.moogleapi.com/api/v1/characters/search"); // sends a http request to moogleAPI to get party member info

        from("direct:party-builder-service-route").
                routeId("party-builder-service-route").
                log("Adding characters for party with id ${exchangeProperty.partyId}").
                process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);

                    exchange.getIn().setBody(body.replaceAll(BRACkETS_REGEX, "")); // removes square brackets from body
                }).
                log("${body}").
                setHeader(Exchange.HTTP_METHOD, HttpMethods.POST).
                setHeader(Exchange.HTTP_PATH, simple("${exchangeProperty.partyId}")).
                removeHeader(Exchange.HTTP_QUERY).
                to("http://localhost:8081/api/members/party"); // sends a http request to local party builder API to add party member
    }
}
