package com.backend.E_commerce.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

//runs once per request
public class JwtValidator extends OncePerRequestFilter {
//    thismethod runs once per reuest and request is incoming request from frontend , response is response that will be sent from BE
//    and filterChain	 is used to continue the request if JWT is valid (pass to next filter/controller)
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        if(jwt!=null && (!jwt.equals(""))){
//            bcoz jwt token by default comes as Bearer token , so we need to remover Bearer and validate actual token
            jwt = jwt.substring(7);
            try{
//                Keys.hmacShaKeyFor(...) creates a secret key for HMAC-SHA-based JWT signature verification.
//                This key is used to check if the token is valid and signed by you.
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
// parserBuilder() prepares a JWT parser.
//setSigningKey(key) tells the parser which secret to use for verification.
//parseClaimsJws(jwt) checks if:
//The token is properly signed (verify the sign in the token
//The token has not expired.
//The token is valid overall.
//getBody() extracts the Claims, which is the data stored inside the token (like email, role, userId etc).
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//                gets email from jwt payload
                String email = String.valueOf(claims.get("email"));
//                gets email from jwt payload i.e user or admin
                String authorities = String.valueOf(claims.get("authorities"));
//              converts   "ROLE_USER,ROLE_ADMIN" into a List of Spring Security roles like:
//              [new SimpleGrantedAuthority("ROLE_USER"),
//              new SimpleGrantedAuthority("ROLE_ADMIN")]
//                it converts your string into the format Spring Security understands for role checking.
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//email	"user@example.com"	This is the user’s identity (called principal)
//null	(password)	We don’t need the password here because JWT already proves identity
//auth	List of authorities	This tells Spring Security what roles this user has (like ROLE_USER, ROLE_ADMIN)
//                This line builds the user’s identity and authorities from the JWT, so Spring Security knows:
                    //Who is making the request (email)
                    //What roles they have (auth)
                    //That the request is already authenticated (because the token was verified)
                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,auth);
//                If the context has a valid Authentication object:
//                  It considers the user authenticated
//                  It checks the user's roles/authorities to allow/deny access
//                It simply says user is validated and request can be processed
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e){
                throw new BadCredentialsException("Invalid token... from jwt validator");
            }
        }
//        “I’ve done my job (like checking JWT). Now, pass the request to the next filter or the controller.”
        filterChain.doFilter(request,response);

    }
}
