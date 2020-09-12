package mps.security.filter;

import lombok.AllArgsConstructor;
import mps.members.dto.MemberDto;
import mps.security.model.MemberProfileDetails;
import mps.security.service.MPSMemberDetailsService;
import mps.util.JwtTokenHelper;
import mps.util.MPSConstant;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static mps.util.MemberUtil.buildMemberDto;


@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MPSMemberDetailsService userDetailsService;
    private final JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String userName = null;
        String jwt = null;

        if (nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userName = jwtTokenHelper.extractUsername(jwt);
        }

        if (nonNull(userName) && (isNull(SecurityContextHolder.getContext().getAuthentication()))) {

            MemberProfileDetails userDetails;
            if(request.getRequestURI().equalsIgnoreCase("/members/create") && MPSConstant.ADMIN_USER.equals(userName))
            {
                userDetails = new MemberProfileDetails(buildMemberDto());
            }
            else {
                userDetails = (MemberProfileDetails) this.userDetailsService.loadUserByUsername(userName);
            }


            if (jwtTokenHelper.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}