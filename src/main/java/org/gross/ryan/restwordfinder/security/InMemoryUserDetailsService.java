package org.gross.ryan.restwordfinder.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.equals("Test")) {
      return User.withUsername("Test").password("Test@1234").roles("USER").build();
    }

    throw new UsernameNotFoundException(username);
  }

}
