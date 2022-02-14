package com.tekenable.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.adeeb.demo.user.model.User;
import com.adeeb.demo.user.repository.UserRepository;
 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SimpleuserregisterationApplicationTests {
     
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void contextLoads() {
    	assertThat(userRepository).isNotNull();
    }
     
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("adeebkhalidch");
        user.setPassword("tekenableDemo");
         
        User savedUser = userRepository.save(user);
         
        User existUser = userRepository.findByUsername(user.getUsername());
         
        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
         
    }
}