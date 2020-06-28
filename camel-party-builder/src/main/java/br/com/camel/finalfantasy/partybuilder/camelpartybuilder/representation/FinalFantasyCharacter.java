package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FinalFantasyCharacter {
    private String id;
    private String name;
    private String age;
    private String gender;
    private String race;
    private String job;
    private String height;
    private String weight;
    private String origin;
    private String description;
    private String picture;
}
