package com.pavan.Jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.Jpa.entity.User;
import com.pavan.Jpa.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository ur;

	public UserService(UserRepository ur) {
		this.ur = ur;
	}
	
	public User createUser(User user) {
        return ur.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return ur.findById(id);
    }

    public List<User> getAllUsers() {
        return ur.findAll();
    }

    public User getUserByEmail(String email) {
        return ur.findByEmail(email);
    }

    public List<User> getUsersByFirstName(String firstName) {
        return ur.findUsersByFirstName(firstName);
    }

    public void deleteUser(Long id) {
        ur.deleteById(id);
    }
}


