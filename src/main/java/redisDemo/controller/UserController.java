package redisDemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import redisDemo.model.User;
import redisDemo.repository.UserRepository;
import redisDemo.service.UserService;
import redisDemo.utils.TokenUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin

public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.POST, value = "/api/User/Register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) throws EntityNotFoundException {
        user.setId(TokenUtil.generateToken());
        service.create(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody Map<String, String> userpass) throws EntityNotFoundException {
        User us = service.findByUsername(userpass.get("userName"));
        if (us != null && us.getPassword().equals(userpass.get("password"))) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        return new ResponseEntity<Object>(service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatedUser(@PathVariable Long id, @RequestBody User updateUser) {
        if (service.findById(id) == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            service.update(updateUser);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
    }
}
