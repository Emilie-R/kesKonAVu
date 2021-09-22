package fr.epita.kesKonAVu.exposition.member.rest;

import fr.epita.kesKonAVu.application.security.AuthenticationService;
import fr.epita.kesKonAVu.application.user.MemberService;
import fr.epita.kesKonAVu.config.security.jwt.JwtResponse;
import fr.epita.kesKonAVu.config.security.jwt.JwtTokenManager;
import fr.epita.kesKonAVu.domain.user.Member;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class MemberAuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenManager jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MemberMapper memberMapper;

    private static final Logger LOG = LoggerFactory.getLogger(MemberAuthenticationController.class);


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ApiOperation(value="This operation allows a member to authenticate itself in KeskonAvu and get a jwtToken.")
    public HttpEntity<?> createAuthenticationToken(@Valid @RequestBody final AuthenticationRequest authenticationRequest) {
        String pass = "";
        if (!authenticationRequest.getPassword().equals("") && authenticationRequest.getPassword() != null) {
            pass = "***";
        }

        LOG.warn("Tentative connexion : pseudo/ password = " + authenticationRequest.getPseudo() + " / " + pass );

        // Rechercher si le member est autorisé à se connecter sinon InvalidCredentialsException
        final Member member = authenticationService.authenticateMember(authenticationRequest.getPseudo(), authenticationRequest.getPassword());

        // Tout est OK - Construction du JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(member.getIdMember());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Construction de la réponse
        final MemberAuthenticatedDTO memberAuthenticatedDTO = memberMapper.mapToLoggedMember(member);
        memberAuthenticatedDTO.setJwtToken(new JwtResponse(token));

        LOG.info("Connexion OK : pseudo/ password/ id Member = " + authenticationRequest.getPseudo() + " / " + pass + " / " + member.getIdMember() );
        return ResponseEntity.ok(memberAuthenticatedDTO);
    }

}
