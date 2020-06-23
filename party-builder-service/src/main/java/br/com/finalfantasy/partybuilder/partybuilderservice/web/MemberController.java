package br.com.finalfantasy.partybuilder.partybuilderservice.web;

import br.com.finalfantasy.partybuilder.partybuilderservice.mapperr.MemberMapper;
import br.com.finalfantasy.partybuilder.partybuilderservice.service.MemberService;
import br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.request.MemberJoiningPartyRequest;
import br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.response.MemberJoiningPartyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/party/{partyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberJoiningPartyResponse addMemberToParty(@PathVariable("partyId") Long partyId, @RequestBody MemberJoiningPartyRequest memberRequest) {
        return MemberMapper.map(memberService.addMemberToParty(partyId, memberRequest));
    }
}
