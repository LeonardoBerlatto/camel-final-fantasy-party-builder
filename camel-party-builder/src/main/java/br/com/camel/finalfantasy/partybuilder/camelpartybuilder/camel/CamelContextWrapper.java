package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelContextWrapper {
    private final CamelContext context;

    public CamelContextWrapper(RouteBuilder... routes) throws Exception {
        this.context = new DefaultCamelContext();
        for (RouteBuilder route :routes) {
            this.context.addRoutes(route);
        }
    }
}
