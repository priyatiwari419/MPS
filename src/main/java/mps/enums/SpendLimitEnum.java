package mps.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@AllArgsConstructor
@Getter
public enum SpendLimitEnum {

    PM_USER(new BigInteger("10000000")),
    MINISTER_USER(new BigInteger("1000000")),
    MP_USER(new BigInteger("100000"));

    private final BigInteger spendLimit;

}
