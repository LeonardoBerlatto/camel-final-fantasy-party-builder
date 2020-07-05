package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.impl;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository.PartyBuilderRepository;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.utils.HttpEntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class PartyBuilderRepositoryImpl implements PartyBuilderRepository {

    public final RestTemplate restTemplate;

    @Value("${party-builder.api.url}")
    private String partyBuilderServiceUri;

    @Override
    public void addCharacterToParty(FinalFantasyCharacter character, long partyId) {
        final var requestURI = partyBuilderServiceUri+"/"+partyId;

        restTemplate.postForEntity(requestURI, HttpEntityUtils.createJSONRequestEntity(character), FinalFantasyCharacter.class);
    }
}
