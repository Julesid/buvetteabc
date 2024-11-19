package com.cheminat.buvetteabc.security;

import com.cheminat.buvetteabc.data.User;
import com.cheminat.buvetteabc.data.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
                    getAuthorities(user));
        }
    }

    private static List<GrantedAuthority> getAuthorities(User user) {

        // Récupérer les rôles associés à l'utilisateur fourni

        List<GrantedAuthority> sesRoles = new ArrayList<>();


        if (user != null && user.getRoles() != null) {

            // Ajout d'une autorité basée sur les rôles de UserABC

            GrantedAuthority g = new SimpleGrantedAuthority("ROLE_" + user.getRoles().toString());

            sesRoles.add(g);

        } else {

            // Gérer le cas où l'utilisateur ou ses rôles sont nuls

            throw new UsernameNotFoundException("User or roles are not found");

        }


        return sesRoles;

    }

}
