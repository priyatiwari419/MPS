package mps.members.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Getter
@Setter
@Valid
public class ExpenditureRequest {
    @NotNull(message = "Spending Amount Can not be Null")
    private BigInteger spendingAmount;
    @NotNull(message = "MemberRole Can not be Null")
    private String memberRole;

}
