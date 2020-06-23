package br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.request;

import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Gender;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoiningPartyRequest {

    private String id;

    private String name;

    private Integer age;

    private String gender;

    private String job;

    private String description;

}
