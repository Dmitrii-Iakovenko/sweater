package com.wutreg.sweater.service;

import com.wutreg.sweater.domen.Role;
import com.wutreg.sweater.entity.User;
import com.wutreg.sweater.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean add(User user) {
        if (isExists(user)) return false;

        user.setActive(true);
        user.setRoles(Set.of(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        if (StringUtils.hasText(user.getEmail())) {
            String message = String.format(
                """
                Hello, %s!
                Welcome to Sweater. Please, visit next link: http://localhost:8080/activate/%s
                """,
                user.getUsername(),
                user.getActivationCode()
            );
            mailService.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    private boolean isExists(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        return userFromDb != null;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) return false;

        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }
}
