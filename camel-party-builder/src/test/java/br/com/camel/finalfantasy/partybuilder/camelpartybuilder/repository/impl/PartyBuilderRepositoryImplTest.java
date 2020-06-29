package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.fixture.FinalFantasyCharacterFixture;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.utils.HttpEntityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl.PartyBuilderRepositoryImpl.PARTY_BUILDER_SERVICE_URI;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Party Builder Repository")
class PartyBuilderRepositoryImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PartyBuilderRepositoryImpl tested;

    @Test
    @DisplayName("Should call the API to add characters to the part")
    void testAddCharacterToParty() {
        final var character = FinalFantasyCharacterFixture.get()
                .withRandomData()
                .build();

        final var response = ResponseEntity.of(Optional.of(character));

        final long partyId = Long.parseLong("23");

        // arrange
        when(restTemplate.postForEntity(
                PARTY_BUILDER_SERVICE_URI + "/" + partyId,
                HttpEntityUtils.createJSONRequestEntity(character),
                FinalFantasyCharacter.class)
        ).thenReturn(response);

        // act
        tested.addCharacterToParty(character, partyId);

        // assert
        verify(restTemplate).postForEntity(
                PARTY_BUILDER_SERVICE_URI + "/" + partyId,
                HttpEntityUtils.createJSONRequestEntity(character),
                FinalFantasyCharacter.class);
    }
}