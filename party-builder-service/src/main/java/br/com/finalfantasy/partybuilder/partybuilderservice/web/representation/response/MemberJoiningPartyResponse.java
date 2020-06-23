package br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.response;

import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoiningPartyResponse {

    private Long id;

    private String name;

    private Integer age;

    private Gender gender;

    private String job;

    private String description;

    private Long partyId;

}
