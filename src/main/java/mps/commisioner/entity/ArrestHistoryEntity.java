package mps.commisioner.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "arrest_history")
@Getter
@Setter
public class ArrestHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "MEMBER_ROLE")
    private String memberRole;
    @Column(name = "MOBILE_NUMBER")
    private int mobileNumber;
    @Column(name = "ARREST_STATUS")
    private String arrestStatus;

}
