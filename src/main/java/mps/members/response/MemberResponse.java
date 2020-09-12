package mps.members.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {

    private Long id;
    private String memberRole;
    private String userName;
    private String password;
    private String permission;
}
