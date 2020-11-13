package com.example.library.services;

import com.example.library.entities.User;
import com.example.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Cacheable(value = "userCache")
    public List<User> getAllUsers(String name, boolean sortByBirthdate) {
        List<User> users = userRepository.findAll();

        if (name != null) {
            users = users.stream()
                    .filter(user -> user.getFirstname().startsWith(name) ||
                            user.getLastname().startsWith(name))
                    .collect(Collectors.toList());
        }

        if (sortByBirthdate) {
            users.sort(Comparator.comparing(User::getBirthdate));
        }
        return users;
    }

    @CachePut(value = "userCache", key = "#result.id")
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Cacheable(value = "userCache", key = "#id")
    public User getUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s.", id));
        }
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s.", username));
        }
        return userRepository.findByUsername(username).get();
    }

    public void deleteUser(String id) {
        if(!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the user by id %s.", id));
        }
        userRepository.deleteById(id);
    }
}
