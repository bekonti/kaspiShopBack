package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.KsRole;
import kz.kbtu.kaspishopback.domain.KsUser;
import kz.kbtu.kaspishopback.repo.RoleRepo;
import kz.kbtu.kaspishopback.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceBean implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KsUser user = userRepo.findByUsername(username);
        if(user == null) {
            log.error("User not found in the db");
            throw new UsernameNotFoundException("User not found in the db");
        }else{
            log.info("User found in db {} ", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public KsUser saveUser(KsUser user) {
        log.info("saving new user {} to db", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public KsRole saveRole(KsRole role) {
        log.info("saving new role {} to db", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {} to user {}", roleName, username);
        KsUser user = getUser(username);
        KsRole role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public KsUser getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<KsUser> getUsers() {
        log.info("Fetching all user");
        return userRepo.findAll();
    }

    @Override
    public KsUser getCurrentUser() {
        return null;
    }


}
