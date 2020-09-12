package mps.security.service;

import lombok.AllArgsConstructor;
import mps.members.dao.MemberDao;
import mps.members.dto.MemberDto;
import mps.security.model.MemberProfileDetails;
import mps.util.MPSConstant;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static mps.util.MemberUtil.buildMemberDto;


@Service
@AllArgsConstructor
public class MPSMemberDetailsService implements UserDetailsService {

	private final MemberDao memberDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
if(nonNull(userName) && MPSConstant.ADMIN_USER.equals(userName))
{
	return new MemberProfileDetails(buildMemberDto());

}
else {
	Optional<MemberDto> memberDtoOptional = memberDao.findByUserName(userName);
	memberDtoOptional.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
	return memberDtoOptional.map(MemberProfileDetails::new).get();
}

	}
}