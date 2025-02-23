package io.aljavap.fillingStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.aljavap.fillingStation.entity.User;
import io.aljavap.fillingStation.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

//   @Autowired
//   private BCryptPasswordEncoder passwordEncoder;


    public void registerUser(User user){
       userRepository.save(user);
    }
    
    public User getByUsername(String username){
       return userRepository.findByUsername(username).orElse(null);
    }
    
    public List<User> getAllUser(){
    	List<User> users = new ArrayList<>();
       userRepository.findAll().forEach(users::add); 
       return users;
    }

    public User getUserByEmail(String email){
       return userRepository.findByEmail(email).orElse(null);
    }

	public User getUserByPhoneNumber(String phoneNumber) {
		
		return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
	}
	
	public boolean getUserByDetails(String details, String password) {

 
		User user = userRepository.findByUsernameOrEmailOrPhoneNumberAndPassword(details, details,
				details, password).orElse(null);
		if (user.getUsername().equals(details) || user.getPhoneNumber().equals(details) || 
				user.getEmail().equals(details) && user.getPassword().equals(password) ) {
			return true;
		} else {
			return false;
		}
		
	}
}