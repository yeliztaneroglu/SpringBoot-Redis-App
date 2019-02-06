package redisDemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redisDemo.model.User;
import redisDemo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User findById(Long id) {
        return repository.findOne(id);
    }

    public User findByUsername(String username) {

        User newUser = new User();
        repository.findAll().forEach(user -> {
            if (user != null) {
                if ((user.getUsername() != null) &&
                        (!user.getUsername().isEmpty()) &&
                        (user.getUsername().equals(username))) {
                    newUser.setId(user.getId());
                    newUser.setPassword(user.getPassword());
                    newUser.setFirstname(user.getFirstname());
                    newUser.setLastname(user.getLastname());
                    newUser.setEmail(user.getEmail());
                    newUser.setUsername(user.getUsername());
                }
            }
        });
        return newUser;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void create(User user) {
        repository.save(user);
    }

    public void update(User updatedUser) {
        User user = repository.findOne(updatedUser.getId());
        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        repository.save(user);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
