package mps.commisioner.response;


import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ExpenditureResponse {
    private BigInteger spendingAmount;
    private String memberRole;
    private boolean isSpendingAmountExceeds;
    private Long memberId;

}
