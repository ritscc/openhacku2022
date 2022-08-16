package cc.rits.openhacku2022.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * User Details Service
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        return this.userRepository.selectByEmail(email) //
            .map(userModel -> new LoginUserDetails(userModel, authorities)) //
            .orElseThrow(() -> new UsernameNotFoundException(null));
    }

}
