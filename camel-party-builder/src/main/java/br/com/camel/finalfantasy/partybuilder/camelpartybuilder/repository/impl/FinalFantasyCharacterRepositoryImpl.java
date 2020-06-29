package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.FinalFantasyCharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class FinalFantasyCharacterRepositoryImpl implements FinalFantasyCharacterRepository {

    public static final String FINAL_FANTASY_CHARACTERS_API_URI = "https://www.moogleapi.com/api/v1/characters/search";

    private final RestTemplate restTemplate;

    @Override
    public List<FinalFantasyCharacter> findCharacterByName(String name) {
        final String requestURI = FINAL_FANTASY_CHARACTERS_API_URI.concat("?name=").concat(name);

        var response = restTemplate.getForEntity(requestURI, FinalFantasyCharacter[].class);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
