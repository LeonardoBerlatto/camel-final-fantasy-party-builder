package br.com.finalfantasy.partybuilder.partybuilderservice.repository;

import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
}
