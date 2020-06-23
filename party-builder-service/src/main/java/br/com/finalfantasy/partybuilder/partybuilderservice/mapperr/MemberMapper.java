package br.com.finalfantasy.partybuilder.partybuilderservice.mapperr;


import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Gender;
import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Member;
import br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.request.MemberJoiningPartyRequest;
import br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.response.MemberJoiningPartyResponse;

public class MemberMapper {

    public static MemberJoiningPartyResponse map(Member member) {
        return MemberJoiningPartyResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .age(member.getAge())
                .job(member.getJob())
                .gender(member.getGender())
                .description(member.getDescription())
                .partyId(member.getParty().getId())
                .build();
    }

    public static Member map(MemberJoiningPartyRequest memberRequest) {
        return Member.builder()
                .name(memberRequest.getName())
                .age(memberRequest.getAge())
                .job(memberRequest.getJob())
                .gender(Gender.valueOf(memberRequest.getGender().toUpperCase()))
                .description(memberRequest.getDescription())
                .build();
    }
}
