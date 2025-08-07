package com.eminpolat.evantproject.business.concretes;

import com.eminpolat.evantproject.dataAccess.UserRepository;
import com.eminpolat.evantproject.entites.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager {
    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public void update(User user) {
        // Eğer user.getId() null değilse ve DB'de varsa, güncelleme yapılır.
        userRepository.save(user);
    }
}
