package org.openlyrics.openlyrics.services;

import org.openlyrics.openlyrics.exception.EmailNotFoundException;
import org.openlyrics.openlyrics.model.User;
import org.openlyrics.openlyrics.model.UserDetailsImpl;
import org.openlyrics.openlyrics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDetailsImpl(user);
    }

    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        User user = this.repository.findByEmail(email)
            .orElseThrow(() -> new EmailNotFoundException(email));
        return new UserDetailsImpl(user);
    }
    
}
