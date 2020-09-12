package mps.members.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Valid
public class MemberRequest {

  @NotNull(message = "First Name can not be NUll")
  private String firstName;
  @NotNull(message = "Last Name can not be NUll")
  private String lastName;
  @Min(value =18,message = "Age can not be less than 18")
  private int age;
  @NotNull(message = "Aadhar Number can not be NUll")
  private Long aadharNumber;
  @NotNull(message = "Voter Id can not be NUll")
  private String voterId;
  @NotNull(message = "Pan Card can not be NUll")
  private String panCard;
  @NotNull(message = "Constituency Name can not be NUll")
  private String constituencyName;
  @NotNull(message = "Member Role can not be NUll")
  private String memberRole;
  @NotNull(message = "Mobile Number can not be NUll")
  private Long mobileNumber;
  private String emailId;
  @NotNull(message = "Driver Name can not be NUll")
  private String driverName;
}
