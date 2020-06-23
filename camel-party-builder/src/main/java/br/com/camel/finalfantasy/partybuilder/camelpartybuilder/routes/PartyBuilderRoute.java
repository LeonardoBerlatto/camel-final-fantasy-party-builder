package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class PartyBuilderRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("file:input?noop=true&include=party_.*\\.xml").
                routeId("main-route").
                log("${body}").
                setProperty("userId", xpath("/party/userId/text()")).
                split().xpath("/party/members/name").
                to("direct:ff-api-route").
                to("direct:party-builder-service-route");

        from("direct:ff-api-route").
                routeId("ff-api-route").
                log("${body}").
                setProperty("name", xpath("/name/text()")).
                log("Querying data from character: ${exchangeProperty.name}").
                setHeader(Exchange.HTTP_METHOD, HttpMethods.GET).
                setHeader(Exchange.HTTP_QUERY, simple("name=${exchangeProperty.name}")).
                to("https://www.moogleapi.com/api/v1/characters/search");
    }
}
