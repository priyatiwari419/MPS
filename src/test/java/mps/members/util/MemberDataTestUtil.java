package mps.members.util;

import mps.members.dto.MemberDto;

public class MemberDataTestUtil {

    public static MemberDto buildDummyMemberDto(){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(1L);
        memberDto.setAge(18);
        memberDto.setFirstName("Priya");
        memberDto.setLastName("Tiwari");
        memberDto.setMemberRole("PM_USER");
        memberDto.setPanCard("ABCD123");
        return memberDto;

    }
}
