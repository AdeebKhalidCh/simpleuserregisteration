package com.adeeb.demo.user.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adeeb.demo.exception.ResourceAlreadyExistsException;
import com.adeeb.demo.user.dto.UserDTO;
import com.adeeb.demo.user.model.User;
import com.adeeb.demo.user.repository.UserRepository;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public void save(UserDTO userDTO)throws ResourceAlreadyExistsException {
    	logger.info("UserService::save");
        //Let's check if user already registered with us
        if(isUserExist(userDTO.getUsername())){
            throw new ResourceAlreadyExistsException("User already exists for this email");
        }
        User user = this.modelMapper.map(userDTO, User.class);
        userRepository.save(user);
    }

    private boolean isUserExist(String username) {
    	logger.info("UserService::isUserExist");
        return userRepository.findByUsername(username) !=null ? true : false;
    }
}