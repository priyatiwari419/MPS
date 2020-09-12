package mps.commisioner.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Valid
public class ArrestHistoryRequest {
  @NotNull(message = "Member Id can not be null")
  private Long memberId;
  @NotNull(message = "Member Role can not be null")
  private String memberRole;

}
