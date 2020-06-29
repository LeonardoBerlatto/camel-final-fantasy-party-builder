package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;

public interface PartyBuilderRepository {

    void addCharacterToParty(FinalFantasyCharacter character, long partyId);
}
