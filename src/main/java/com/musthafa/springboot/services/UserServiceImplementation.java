package com.musthafa.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musthafa.springboot.models.UserEntity;
import com.musthafa.springboot.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserEntity> listUsers() {
		// TODO Auto-generated method stub
		List<UserEntity> result = (List<UserEntity>) userRepository.findAll();
		if (result.isEmpty())
			return new ArrayList<UserEntity>();
		else
			return result;
	}

	@Override
	public void addUser(UserEntity user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public void deleteUserById(int userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

	@Override
	public void updateUser(UserEntity user) {
		// TODO Auto-generated method stub
		userRepository.update(user);
	}

	@Override
	public Optional<UserEntity> listUserById(int userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}

}
