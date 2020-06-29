package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.processor;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Final Fantasy Character Processor")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FinalFantasyCharacterProcessorTest {

    @Mock
    private Exchange exchange;

    @Mock
    private Message message;

    @InjectMocks
    private FinalFantasyCharacterProcessor tested;

    @BeforeAll
    void init() {
        tested = new FinalFantasyCharacterProcessor();
    }

    @Test
    @DisplayName("Should transform body into Fina lFantasy Character")
    void testProcess() throws Exception {
        when(exchange.getIn())
                .thenReturn(message);

        tested.process(exchange);

        verify(exchange, times(2)).getIn();
        verify(message, times(1)).getBody(FinalFantasyCharacter.class);
        verify(message, times(1)).setBody(any());
    }
}