package com.hairdresser.booking.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder
public class ApplicationUser implements UserDetails {

    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;
    public final String id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public String getId() {
        return id;
    }
}
