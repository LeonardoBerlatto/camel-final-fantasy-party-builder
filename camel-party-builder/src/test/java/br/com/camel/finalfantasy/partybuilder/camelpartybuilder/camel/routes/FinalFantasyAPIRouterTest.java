package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.base.RouteTest;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.CamelContextWrapper;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.fixture.FinalFantasyCharacterFixture;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.FinalFantasyCharacterRepository;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes.FinalFantasyAPIRouter.FINAL_FANTASY_API_ROUTE_URI;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Final Fantasy API Router")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FinalFantasyAPIRouterTest extends RouteTest {

    @Mock
    private FinalFantasyCharacterRepository repository;

    @InjectMocks
    private FinalFantasyAPIRouter tested;

    private CamelContextWrapper context;

    private ProducerTemplate producerTemplate;

    @BeforeEach
    void setup() throws Exception {
        context = new CamelContextWrapper(tested);
        producerTemplate = context.createProducerTemplate();

        context.getContext().start();
    }

    @Test
    @DisplayName("Should extract from XML and call repository")
    void testConfigure() {
        // arrange
        final FinalFantasyCharacter character = FinalFantasyCharacterFixture.get()
                .withRandomData()
                .build();

        when(repository.findCharacterByName(character.getName()))
                .thenReturn(Collections.singletonList(
                        character
                ));

        // act
        producerTemplate.requestBody(FINAL_FANTASY_API_ROUTE_URI, "<name>" + character.getName() + "</name>");

        // assert
        verify(repository).findCharacterByName(anyString());
    }
}