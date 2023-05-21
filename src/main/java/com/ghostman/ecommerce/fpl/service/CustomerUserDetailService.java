package com.ghostman.ecommerce.fpl.service;

import com.ghostman.ecommerce.fpl.model.CustomerUserDetail;
import com.ghostman.ecommerce.fpl.model.User;
import com.ghostman.ecommerce.fpl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findUserByEmail("email");
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Spring will require the user detail object to be created or passed
        return user.map(CustomerUserDetail::new).get();
    }
}
