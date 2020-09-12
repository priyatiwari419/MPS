package mps.commisioner.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrestHistoryResponse {
    private Long arrestHistoryId;
    private Long memberId;
    private String memberName;
    private String memberAddress;
    private int mobileNumber;
    private String memberRole;
    private String arrestStatus;
}
