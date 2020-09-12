package mps.members.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Long aadharNumber;
    private String voterId;
    private String panCard;
    private String constituencyName;
    private String memberRole;
    private Long mobileNumber;
    private String emailId;
    private String driverName;
    private String userName;
    private String password;
    private String permission;
}
