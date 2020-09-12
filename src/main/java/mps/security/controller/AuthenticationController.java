package mps.security.controller;

import lombok.AllArgsConstructor;
import mps.members.dto.MemberDto;
import mps.security.model.MemberProfileDetails;
import mps.security.request.AuthenticationRequest;
import mps.security.response.AuthenticationResponse;
import mps.security.service.MPSMemberDetailsService;
import mps.util.JwtTokenHelper;
import mps.util.MPSConstant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static mps.util.MemberUtil.buildMemberDto;

@RestController
@RequestMapping("/mps")
@AllArgsConstructor
@Validated
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenHelper jwtTokenUtil;
	private final MPSMemberDetailsService userDetailsService;

	@PostMapping(value = "/token")
 public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest){
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		MemberProfileDetails userDetails;
if(MPSConstant.ADMIN_USER.equals(authenticationRequest.getUserName()) && MPSConstant.ADMIN_PASSWORD.equals(authenticationRequest.getUserName()))
{
	userDetails = new MemberProfileDetails(buildMemberDto());
}
else
{
	userDetails = (MemberProfileDetails) userDetailsService
			.loadUserByUsername(authenticationRequest.getUserName());
}
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(jwt);
		response.setId(userDetails.getId());
		response.setUsername(userDetails.getUsername());
		List<String> roles = new ArrayList<String>();
		userDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
		response.setRoles(roles);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
	
	}
}