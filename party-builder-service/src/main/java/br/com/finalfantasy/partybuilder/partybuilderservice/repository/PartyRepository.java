package br.com.finalfantasy.partybuilder.partybuilderservice.repository;

import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {
}
