package mps.security.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String jwt;
    private Long id;
	private String username;
    private List<String> roles;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
