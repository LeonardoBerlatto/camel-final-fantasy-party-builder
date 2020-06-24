package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MemberRequestProcessor implements Processor {

    public static final String BRACKETS_REGEX = "[\\[\\]']+";

    @Override
    public void process(Exchange exchange) throws Exception {
            String body = exchange.getIn().getBody(String.class);

            exchange.getIn().setBody(body.replaceAll(BRACKETS_REGEX, "")); // removes square brackets from body
    }
}
