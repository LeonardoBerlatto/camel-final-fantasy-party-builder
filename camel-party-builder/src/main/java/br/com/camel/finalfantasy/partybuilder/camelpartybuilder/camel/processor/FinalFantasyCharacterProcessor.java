package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.processor;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FinalFantasyCharacterProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
            var body = exchange.getIn().getBody(FinalFantasyCharacter.class);

            exchange.getIn().setBody(body);
    }
}
