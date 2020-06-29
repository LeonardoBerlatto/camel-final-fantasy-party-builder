package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.CamelContextWrapper;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.fixture.FinalFantasyCharacterFixture;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.PartyBuilderRepository;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes.PartyBuilderAPIRouter.PARTY_BUILDER_SERVICE_ROUTE_URI;
import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.camel.routes.PartyBuilderRouter.PARTY_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Party Builder API Router")
class PartyBuilderAPIRouterTest {

    private CamelContextWrapper contextWrapperTest;

    private ProducerTemplate producerTemplateTest;

    @Mock
    private PartyBuilderRepository repository;

    @InjectMocks
    private PartyBuilderAPIRouter tested;

    @BeforeEach
    void setup() throws Exception {
        contextWrapperTest = new CamelContextWrapper(tested);
        producerTemplateTest = contextWrapperTest.createProducerTemplate();

        contextWrapperTest.getContext().start();
    }

    @Test
    @DisplayName("Should process body and call repository")
    void testConfigure() {
        // arrange
        final var body = FinalFantasyCharacterFixture.get().withRandomData().build();

        doNothing()
                .when(repository).addCharacterToParty(body, 1);

        // act
        producerTemplateTest.requestBodyAndHeader(PARTY_BUILDER_SERVICE_ROUTE_URI, body, PARTY_ID, "1");

        // assert
        verify(repository).addCharacterToParty(any(FinalFantasyCharacter.class), anyLong());

    }
}