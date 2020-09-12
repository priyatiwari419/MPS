package mps.commisioner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrestHistoryDto {
    private Long memberId;
    private String memberRole;
    private String arrestStatus;
}
