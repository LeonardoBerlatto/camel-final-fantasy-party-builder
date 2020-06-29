package br.com.finalfantasy.partybuilder.partybuilderservice.service;

import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Gender;
import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Member;
import br.com.finalfantasy.partybuilder.partybuilderservice.domain.Party;
import br.com.finalfantasy.partybuilder.partybuilderservice.exception.NotFoundException;
import br.com.finalfantasy.partybuilder.partybuilderservice.repository.MemberRepository;
import br.com.finalfantasy.partybuilder.partybuilderservice.repository.PartyRepository;
import br.com.finalfantasy.partybuilder.partybuilderservice.web.representation.request.MemberJoiningPartyRequest;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Member Service")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService tested;

    @Test
    @DisplayName("Should add a member to the party")
    void testAddMemberToParty_success() {
        // arrange
        final Long partyId = Long.valueOf("23");
        final Party party = Party.builder()
                .id(partyId)
                .build();

        when(partyRepository.findById(partyId))
                .thenReturn(Optional.of(party));

        final String name = RandomString.make(12);

        final var request = MemberJoiningPartyRequest.builder()
                .name(name)
                .gender("Female")
                .build();

        final var member = Member.builder()
                .id(12L)
                .name(name)
                .gender(Gender.FEMALE)
                .build();

        when(memberRepository.save(any(Member.class)))
                .thenReturn(member);
        // act
        var result = tested.addMemberToParty(partyId, request);

        // assert
        verify(partyRepository).findById(partyId);
        verify(memberRepository).save(any(Member.class));
        assertEquals(member, result);
    }

    @Test
    @DisplayName("Should add a member to the party")
    void testAddMemberToParty_partyNotFound() {
        // arrange
        final Long partyId = Long.valueOf("23");

        when(partyRepository.findById(partyId))
                .thenReturn(Optional.empty());

        final var request = MemberJoiningPartyRequest.builder()
                .name(RandomString.make(12))
                .gender("Female")
                .build();
        // act
        assertThrows(NotFoundException.class, () -> tested.addMemberToParty(partyId, request));

        // assert
        verify(partyRepository).findById(partyId);
        verify(memberRepository, times(0)).save(any(Member.class));
    }
}