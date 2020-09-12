package mps.members.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "AGE")
    private int age;
    @Column(name = "AADHAR_NUMBER",unique = true)
    private Long aadharNumber;
    @Column(name = "VOTER_ID",unique = true)
    private String voterId;
    @Column(name = "PAN_CARD",unique = true)
    private String panCard;
    @Column(name = "CONSTITUENCY_NAME")
    private String constituencyName;
    @Column(name = "MEMBER_ROLE")
    private String memberRole;
    @Column(name = "MOBILE_NUMBER")
    private Long mobileNumber;
    @Column(name = "EMAIL_ID")
    private String emailId;
    @Column(name = "DRIVER_NAME")
    private String driverName;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PERMISSION")
    private String permission;
    @OneToOne(mappedBy = "memberEntity",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private ExpenditureEntity expenditureEntity;

}
