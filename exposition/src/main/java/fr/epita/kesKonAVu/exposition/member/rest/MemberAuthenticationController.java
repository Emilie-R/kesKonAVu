package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.user.Member;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MemberAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenManager jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ApiOperation(value="This operation allows a member to authenticate itself in KeskonAvu and get a Token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final AuthenticationRequest authenticationRequest) throws Exception {

        // Convertir le pseudo en idMember
        final Member member = memberService.findOne(authenticationRequest.getPseudo());

        // Fonction lève une exception si idMember/mot de passe sont KO
        authenticate(member.getIdMember(), authenticationRequest.getPassword());

        // Construction du JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(member.getIdMember());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Construction de la réponse
        final MemberAuthenticatedDTO memberAuthenticatedDTO = memberMapper.mapToLoggedMember(member);
        memberAuthenticatedDTO.setJwtToken(new JwtResponse(token));

        return ResponseEntity.ok(memberAuthenticatedDTO);
    }

    private void authenticate(final String pseudo, final String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(pseudo, password));

        } catch (final DisabledException e) {
            throw new Exception("USER_DISABLED", e);

        } catch (final BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
