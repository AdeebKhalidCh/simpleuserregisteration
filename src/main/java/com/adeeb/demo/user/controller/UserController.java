package com.adeeb.demo.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.adeeb.demo.exception.ResourceAlreadyExistsException;
import com.adeeb.demo.user.dto.UserDTO;
import com.adeeb.demo.user.service.UserService;
import com.adeeb.demo.user.validator.UserValidator;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UserValidator userValidator;
 
    @InitBinder("userDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }
    
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String getSignUpView(final Model model){
    	logger.info("UserController::getSignUpView");
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String userSignUp(final @Valid  UserDTO userDTO, final BindingResult bindingResult, final Model model){
    	logger.info("UserController::userSignUp");
    	if(bindingResult.hasErrors()){
            return "signup";
        }
        try {
            userService.save(userDTO);
        }catch (ResourceAlreadyExistsException e){
        	logger.error(e.getMessage());
            bindingResult.rejectValue("username","validation.error.message.userName.exists");
            return "signup";
        }
        return "signup-success";
    }
}
