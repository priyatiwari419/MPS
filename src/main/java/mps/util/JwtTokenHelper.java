package mps.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mps.enums.MPSMemberRoleEnum;
import mps.security.model.MemberProfileDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

    private String SECRET_KEY = "secret";

      public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(MemberProfileDetails memberProfileDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, memberProfileDetails.getUsername(),memberProfileDetails.getMemberRole());
    }


    public Boolean validateToken(String token, MemberProfileDetails memberProfileDetails) {
        final String username = extractUsername(token);
        final boolean userRole = validateRoleFromToken(memberProfileDetails.getMemberRole(),extractUserRole(token));
        return (username.equals(memberProfileDetails.getUsername()) && !isTokenExpired(token) && userRole);
    }

    public String extractUserRole(String token){
        return extractClaim(token, Claims::getAudience);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static boolean validateRoleFromToken(String userRoleFromMemberDetails,String roleFromToken)
    {
        return userRoleFromMemberDetails.equalsIgnoreCase(roleFromToken);
    }

    public boolean validateRoleForArrest(String token,String memberRole)
    {
           if (MPSMemberRoleEnum.valueOf(extractUserRole(token)).name().equalsIgnoreCase(MPSMemberRoleEnum.COMMISSIONER_USER.name()))
           {
               return !MPSMemberRoleEnum.valueOf(memberRole).name().equalsIgnoreCase(MPSMemberRoleEnum.PM_USER.name())
                       && !MPSMemberRoleEnum.valueOf(memberRole).name().equalsIgnoreCase(MPSMemberRoleEnum.MINISTER_USER.name())
                       && !MPSMemberRoleEnum.valueOf(memberRole).name().equalsIgnoreCase(MPSMemberRoleEnum.SPECIAL_COMMISSIONER_USER.name()) ;
           }
           else if(MPSMemberRoleEnum.valueOf(extractUserRole(token)).name().equalsIgnoreCase(MPSMemberRoleEnum.SPECIAL_COMMISSIONER_USER.name()))
           {
               return !MPSMemberRoleEnum.valueOf(memberRole).name().equalsIgnoreCase(MPSMemberRoleEnum.PM_USER.name());
           }
           else
           {
               return true;
           }


    }

    public boolean validateTokenForDrivers(String token)
    {
        return MPSMemberRoleEnum.valueOf(extractUserRole(token)).name().equalsIgnoreCase(MPSMemberRoleEnum.PM_USER.name());
    }

    private String createToken(Map<String, Object> claims, String subject,String userRole) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setAudience(userRole).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}