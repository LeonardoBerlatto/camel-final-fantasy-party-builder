package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.repository;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;

import java.util.List;

public interface FinalFantasyCharacterRepository {

    List<FinalFantasyCharacter> findCharacterByName(String name);
}
