package br.com.finalfantasy.partybuilder.partybuilderservice.service;

import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Member;
import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Party;
import br.com.finalfantasy.partybuilder.partybuilderservice.exception.NotFoundException;
import br.com.finalfantasy.partybuilder.partybuilderservice.mapperr.MemberMapper;
import br.com.finalfantasy.partybuilder.partybuilderservice.repository.MemberRepository;
import br.com.finalfantasy.partybuilder.partybuilderservice.repository.PartyRepository;
import br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.request.MemberJoiningPartyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final PartyRepository partyRepository;

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(PartyRepository partyRepository, MemberRepository memberRepository) {
        this.partyRepository = partyRepository;
        this.memberRepository = memberRepository;
    }

    public Member addMemberToParty(Long partyId, MemberJoiningPartyRequest memberRequest) {
       var party = partyRepository
               .findById(partyId)
               .orElseThrow(() -> new NotFoundException("Party not found."));

        var member = MemberMapper.map(memberRequest);
        member.setParty(party);

       return memberRepository.save(member);
    }
}
