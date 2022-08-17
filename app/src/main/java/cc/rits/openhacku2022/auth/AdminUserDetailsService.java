package cc.rits.openhacku2022.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * User Details Service
 */
@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

    private final ShopRepository shopRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        final var authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        return this.shopRepository.selectByCode(code) //
            .map(shopModel -> new AdminUserDetails(shopModel, authorities)) //
            .orElseThrow(() -> new UsernameNotFoundException(null));
    }

}
