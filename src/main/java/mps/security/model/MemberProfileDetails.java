package mps.security.model;

import lombok.Getter;
import lombok.Setter;
import mps.members.dto.MemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemberProfileDetails implements UserDetails {

	private static final long serialVersionUID = 275347623L;
	private Long id;
	private String username;
    private String password;
    private String memberRole;
    private List<GrantedAuthority> authorities;

    public MemberProfileDetails(MemberDto memberDto) {
    	this.id = memberDto.getId();
        this.username = memberDto.getUserName();
        this.password = memberDto.getPassword();
        this.memberRole = memberDto.getMemberRole();
        this.authorities = Arrays.stream(memberDto.getMemberRole().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
