package mps.members.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ExpenditureDto {
    private BigInteger spendingAmount;
    private String memberRole;
    private boolean isSpendingAmountExceeds;
    private Long memberId;
}
