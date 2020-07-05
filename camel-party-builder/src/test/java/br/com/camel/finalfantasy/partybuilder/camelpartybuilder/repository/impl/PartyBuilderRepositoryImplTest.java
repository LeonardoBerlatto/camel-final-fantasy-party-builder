package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.fixture.FinalFantasyCharacterFixture;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.utils.HttpEntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Party Builder Repository")
class PartyBuilderRepositoryImplTest {

    private final String PARTY_BUILDER_API_URI = "http://test";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PartyBuilderRepositoryImpl tested;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils
                .setField(tested, "partyBuilderServiceUri", PARTY_BUILDER_API_URI);
    }

    @Test
    @DisplayName("Should call the API to add characters to the party")
    void testAddCharacterToParty() {
        // arrange
        final var character = FinalFantasyCharacterFixture.get()
                .withRandomData()
                .build();

        final var response = ResponseEntity.of(Optional.of(character));

        final long partyId = Long.parseLong("23");

        final HttpEntity httpEntity = HttpEntityUtils.createJSONRequestEntity(character);
        when(restTemplate.postForEntity(
                PARTY_BUILDER_API_URI+"/"+partyId,
                httpEntity,
                FinalFantasyCharacter.class)
        ).thenReturn(response);

        // act
        tested.addCharacterToParty(character, partyId);

        // assert
        verify(restTemplate).postForEntity(
                PARTY_BUILDER_API_URI+"/"+partyId,
                httpEntity,
                FinalFantasyCharacter.class);
    }
}