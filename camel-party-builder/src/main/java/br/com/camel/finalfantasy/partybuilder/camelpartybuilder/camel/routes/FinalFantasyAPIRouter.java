package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.springframework.stereotype.Component;

@Component
public class FinalFantasyAPIRouter extends RouteBuilder {

    public static final String FINAL_FANTASY_API_ROUTE_URI = "direct:ff-api-route";

    public static final String PARTY_MEMBER_NAME = "name";

    public static final String FINAL_FANTASY_CHARACTERS_API_URI = "https://www.moogleapi.com/api/v1/characters/search";

    @Override
    public void configure() throws Exception {
        from(FINAL_FANTASY_API_ROUTE_URI).
                routeId("ff-api-route").
                log("${body}").
                setProperty(PARTY_MEMBER_NAME, xpath("/name/text()")).
                log("Querying data from character: ${exchangeProperty.name}").
                setHeader(Exchange.HTTP_METHOD, HttpMethods.GET).
                setHeader(Exchange.HTTP_QUERY, simple("name=${exchangeProperty.name}")).
                to(FINAL_FANTASY_CHARACTERS_API_URI); // sends a http request to moogleAPI to get party member info
    }
}
