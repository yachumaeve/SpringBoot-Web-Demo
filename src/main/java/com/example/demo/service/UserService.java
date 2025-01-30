package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public void deleteUsers(String id) {
		userRepository.deleteById(id);
	}
	public User updateUser(String id, User userDetails) {
		User user = userRepository.findById(id).get();
		user.setEmail(userDetails.getEmail());
		user.setName(userDetails.getName());
		user.setPassword(userDetails.getPassword());
		user.setPhone(userDetails.getPhone());
		user.setRoles(userDetails.getRoles());
		
		return userRepository.save(user);
	}
	
	public Boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }
	
	public User login(User user) {
		User completed_user = getUserByEmailAndPassword(user.getEmail(),user.getPassword());
		return completed_user;
		
	}
	
	
}

