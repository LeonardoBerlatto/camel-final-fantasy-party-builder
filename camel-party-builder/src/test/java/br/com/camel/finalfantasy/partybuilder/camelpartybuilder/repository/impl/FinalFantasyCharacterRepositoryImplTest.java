package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.fixture.FinalFantasyCharacterFixture;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl.FinalFantasyCharacterRepositoryImpl.FINAL_FANTASY_CHARACTERS_API_URI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Final Fantasy Repository")
@ExtendWith(MockitoExtension.class)
class FinalFantasyCharacterRepositoryImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FinalFantasyCharacterRepositoryImpl tested;

    @Test
    @DisplayName("Should return a list of characts with the name passed by parameter")
    void testFindCharacterByName() {
        // arrange
        final var name = RandomString.make(20);

        final var character = FinalFantasyCharacterFixture.get()
                .withRandomData()
                .build();

        final var response = ResponseEntity.of(Optional.of(new FinalFantasyCharacter[]{character}));

        final String uri = FINAL_FANTASY_CHARACTERS_API_URI.concat("?name=").concat(name);

        when(restTemplate.getForEntity(
                uri,
                FinalFantasyCharacter[].class)
        ).thenReturn(response);


        // act
        List<FinalFantasyCharacter> result = tested.findCharacterByName(name);

        // assert
        verify(restTemplate).getForEntity(
                uri,
                FinalFantasyCharacter[].class);
        assertEquals(character, result.get(0));
    }
}