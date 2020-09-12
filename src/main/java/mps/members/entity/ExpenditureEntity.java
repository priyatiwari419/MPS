package mps.members.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "expenditure")
@Getter
@Setter
public class ExpenditureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPENDING_AMOUNT")
    private BigInteger spendingAmount;

    @Column(name="IS_SPENDING_AMOUNT_EXCEEDS")
    private boolean isSpendingAmountExceeds;

    @Column(name="MEMBER_ROLE")
    private String memberRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    private MemberEntity memberEntity;



}
