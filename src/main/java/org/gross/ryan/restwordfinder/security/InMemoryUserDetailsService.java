package org.gross.ryan.restwordfinder.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.equals("Test")) {
      return User.withUsername("Test")
              .password("$2a$04$4yS/y6hM3gJTL/EwUi5OteYtvSSMZGxjYkV1u2N4KQWheFMk1s0vu")
              .roles("USER").build();
    }

    throw new UsernameNotFoundException(username);
  }

}
