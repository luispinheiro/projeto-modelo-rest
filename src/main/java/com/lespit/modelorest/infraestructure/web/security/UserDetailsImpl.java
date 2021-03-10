package com.lespit.modelorest.infraestructure.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.lespit.modelorest.domain.user.AppUser;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	private String username;
	private String password;
	private String displayName;
	
	public UserDetailsImpl(AppUser appUser) {
		this.username = appUser.getUsername();
		this.password = appUser.getPassword();
		this.displayName = appUser.getDisplayName();
	}

	@Override // retorno de ROLES de acesso, neste caso não teremos perfil de acesso
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override //conta não esta expirada
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override //conta não bloqueada
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override //credencial não expirada
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override // Esta habilitada
	public boolean isEnabled() {
		return true;
	}

}