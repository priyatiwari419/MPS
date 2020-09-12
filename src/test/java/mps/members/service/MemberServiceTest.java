package mps.members.service;

import mps.exception.MemberNotFoundException;
import mps.members.dao.MemberDao;
import mps.members.dto.MemberDto;
import mps.util.JwtTokenHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static mps.members.util.MemberDataTestUtil.buildDummyMemberDto;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberServiceTest {


   @Mock private MemberDao memberDao;
   @Mock private JwtTokenHelper jwtTokenHelper;
   @InjectMocks private MPSMemberService mpsMemberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_MemberData_Test(){
        MemberDto memberDto = buildDummyMemberDto();
        when(memberDao.createMember(memberDto)).thenReturn(Optional.of(memberDto));
        MemberDto memberDto1 = mpsMemberService.createMember(memberDto);
        assertNotNull(memberDto1);
        verify(memberDao).createMember(memberDto);
    }

    @Test
    public void validateMemberDto_ThrowException(){
        Long memberId = 1L;
        when(memberDao.validationFromDb(memberId)).thenReturn(Optional.empty());
        assertThrows(
                MemberNotFoundException.class,
                () -> mpsMemberService.validateMemberDto(null));
    }
}
