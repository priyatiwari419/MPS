package mps.security.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@Valid
public class AuthenticationRequest implements Serializable {
    @NotNull(message = "User Name Can not be Null")
    private String userName;
    @NotNull(message = "Password Can not be Null")
    private String password;
}
