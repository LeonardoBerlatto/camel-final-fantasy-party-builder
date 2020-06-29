package br.com.camel.finalfantasy.partybuilder.camelpartybuilder.fixture;

import br.com.camel.finalfantasy.partybuilder.camelpartybuilder.representation.FinalFantasyCharacter;
import net.bytebuddy.utility.RandomString;

public class FinalFantasyCharacterFixture {

    private FinalFantasyCharacter finalFantasyCharacter;

    private FinalFantasyCharacterFixture() {
        this.finalFantasyCharacter = new FinalFantasyCharacter();
    }

    public static FinalFantasyCharacterFixture get() {
        return new FinalFantasyCharacterFixture();
    }

    public FinalFantasyCharacterFixture withRandomData() {
        this.finalFantasyCharacter = FinalFantasyCharacter.builder()
                .id(RandomString.make(20))
                .name(RandomString.make(20))
                .age(RandomString.make(2))
                .description(RandomString.make(200))
                .gender(RandomString.make(10))
                .height(RandomString.make(5))
                .job(RandomString.make(20))
                .origin(RandomString.make(20))
                .picture(RandomString.make(50))
                .race(RandomString.make(10))
                .weight(RandomString.make(10))
                .build();

        return this;
    }

    public FinalFantasyCharacter build() {
        return finalFantasyCharacter;
    }
}
