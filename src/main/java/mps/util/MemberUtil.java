package mps.util;

import mps.members.dto.MemberDto;

public class MemberUtil {

    public static MemberDto buildMemberDto(){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(0L);
        memberDto.setUserName("ADMIN");
        memberDto.setPassword("PASSWORD");
        memberDto.setMemberRole("ADMIN_USER");
        return memberDto;
    }


}
