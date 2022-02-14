package com.adeeb.demo.user.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.adeeb.demo.user.dto.UserDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
@Component
public class UserValidator implements Validator {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String ALPHA_NUMERIC = "^[a-zA-Z0-9\\s]*$";
	public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
 
    @Override
    public boolean supports(Class clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }
 
    @Override
    public void validate(Object target, Errors errors) {
    	UserDTO userDTO = (UserDTO) target;
    	logger.info("UserValidator::validate");
 
        if(StringUtils.isEmpty(userDTO.getUsername())) {
        	errors.rejectValue("username", "validation.error.message.userName.required");
        }else if(!objectValidAgainstRegexPattern(userDTO.getUsername(), ALPHA_NUMERIC)) {
        	errors.rejectValue("username", "validation.error.message.userName.invalid");
        }
        if(StringUtils.isEmpty(userDTO.getPassword())) {
        	errors.rejectValue("password", "validation.error.message.password.required");
        } else if(!objectValidAgainstRegexPattern(userDTO.getPassword(), PASSWORD_PATTERN) || userDTO.getPassword().length() < 8) {
        	errors.rejectValue("password", "validation.error.message.password.invalid");
        }
 
    }
    
    private static boolean objectValidAgainstRegexPattern(String obj, String regexPattern){
        boolean isTrue = false;
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(obj);
        if(matcher.matches()) {
            isTrue = true;
        }
        return isTrue;
    }
 
}