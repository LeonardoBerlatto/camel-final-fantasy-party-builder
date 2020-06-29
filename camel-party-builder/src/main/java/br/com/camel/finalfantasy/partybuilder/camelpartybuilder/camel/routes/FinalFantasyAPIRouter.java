package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.FinalFantasyCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinalFantasyAPIRouter extends RouteBuilder {

    public static final String FINAL_FANTASY_API_ROUTE_URI = "direct:ff-api-route";

    private final FinalFantasyCharacterRepository repository;

    @Override
    public void configure() throws Exception {
        from(FINAL_FANTASY_API_ROUTE_URI).
                routeId("ff-api-route").
                log("${body}").
                setBody(xpath("/name/text()")).
                log("Querying data from character: ${body}").
                bean(repository); // sends a http request to moogleAPI to get party member info
    }
}
