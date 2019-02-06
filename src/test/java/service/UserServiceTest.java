package service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import redisDemo.model.User;
import redisDemo.repository.UserRepository;
import redisDemo.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserService.class})
public class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(120);
        user.setUsername("githubCat");
        user.setEmail("githubCat@gmail.com");
        user.setLastname("Cat");
        user.setFirstname("github");
        user.setPassword("githubCat");

        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThat(service.findById(user.getId()));
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(120);
        user.setUsername("githubCat");
        user.setEmail("githubCat@gmail.com");
        user.setLastname("Cat");
        user.setFirstname("github");
        user.setPassword("githubCat");

        Mockito.when(userRepository.findOne(Long.valueOf("120"))).thenReturn(user);
        assertThat(service.findById(Long.valueOf("120"))).isEqualTo(user);

    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(120);
        user1.setUsername("githubCat");
        user1.setEmail("githubCat@gmail.com");
        user1.setLastname("Cat");
        user1.setFirstname("github");
        user1.setPassword("githubCat");

        User user2 = new User();
        user2.setId(122);
        user2.setUsername("hubCat");
        user2.setEmail("hubCat@gmail.com");
        user2.setLastname("Dog");
        user2.setFirstname("hub");
        user2.setPassword("hubCat");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        assertThat(service.getAll()).isEqualTo(userList);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(120);
        user.setUsername("githubCat");
        user.setEmail("githubCat@gmail.com");
        user.setLastname("Cat");
        user.setFirstname("github");
        user.setPassword("githubCat");

        Mockito.when(userRepository.findOne(Long.valueOf("120"))).thenReturn(user);
        Mockito.when(userRepository.exists(user.getId())).thenReturn(false);
        assertFalse(userRepository.exists(user.getId()));
    }


}
