package br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class MemberJoiningPartyRequest {

    private String id;

    private String name;

    private Integer age;

    private String gender;

    private String job;

    private String description;

}
