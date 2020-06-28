package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.PartyBuilderRepository;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.utils.HttpEntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class PartyBuilderRepositoryImpl implements PartyBuilderRepository {

    public final RestTemplate restTemplate;

    public static final String PARTY_BUILDER_SERVICE_URI = "http://localhost:8081/api/members/party";

    @Override
    public void addCharacterToParty(FinalFantasyCharacter character, int partyId) {
        final var requestURI = PARTY_BUILDER_SERVICE_URI+"/"+partyId;

        restTemplate.postForEntity(requestURI, HttpEntityUtils.createJSONRequestEntity(character), FinalFantasyCharacter.class);
    }
}
