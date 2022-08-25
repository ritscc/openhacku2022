package cc.rits.openhacku2022.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cc.rits.openhacku2022.model.ShopModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Shop User Details
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUserDetails extends ShopModel implements UserDetails {

    /**
     * authorities
     */
    Collection<? extends GrantedAuthority> authorities;

    public AdminUserDetails(final ShopModel shop, final Collection<? extends GrantedAuthority> authorities) {
        this.setId(shop.getId());
        this.setName(shop.getName());
        this.setCode(shop.getCode());
        this.setPassword(shop.getPassword());
        this.setTables(shop.getTables());
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return this.getCode();
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
