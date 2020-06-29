package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel;


import lombok.Getter;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

@Getter
public class CamelContextWrapper {
    private final CamelContext context;

    public CamelContextWrapper(RouteBuilder... routes) throws Exception {
        this.context = new DefaultCamelContext();
        for (RouteBuilder route : routes) {
            this.context.addRoutes(route);
        }
    }

    public ProducerTemplate createProducerTemplate() {
        return this.context.createProducerTemplate();
    }
}
