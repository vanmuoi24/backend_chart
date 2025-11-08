package com.example.chart_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.chart_backend.dto.request.ResCreateUserDTO;
import com.example.chart_backend.entity.User;
import com.example.chart_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User handleGetUserByUserNawm(String username) {
        return this.userRepository.findByUserEmail(username);
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User handleUpdateUser(User user) {
        User userUPdate = this.fetchUserById(user.getId());

        if (userUPdate != null) {

            userUPdate.setUserPhone(user.getUserPhone());

        }
        return this.userRepository.save(userUPdate);
    }

    public User fetchUserById(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public void updateUserToken(String refreshToken, String username) {
        User user = handleGetUserByUserNawm(username);
        if (user != null) {
            userRepository.save(user);
        }
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public ResCreateUserDTO convertToResCreateUserDTO(User user) {
        ResCreateUserDTO resCreateUserDTO = new ResCreateUserDTO();
        resCreateUserDTO.setId(user.getId());
        resCreateUserDTO.setUserEmail(user.getUserEmail());
        resCreateUserDTO.setUserFullname(user.getUserFullname());
        resCreateUserDTO.setUserPhone(user.getUserPhone());
        resCreateUserDTO.setUserRole(user.getUserRole());
        return resCreateUserDTO;
    }

    public User updateUser(Long id, User userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // cập nhật các trường cần thiết
        if (userUpdate.getUserFullname() != null) {
            user.setUserFullname(userUpdate.getUserFullname());
        }
        if (userUpdate.getUserEmail() != null) {
            user.setUserEmail(userUpdate.getUserEmail());
        }
  
        if (userUpdate.getUserRole() != null) {
            user.setUserRole(userUpdate.getUserRole());
        }
        if (userUpdate.getUserPhone() != null) {
            user.setUserPhone(userUpdate.getUserPhone());
        }
        return userRepository.save(user);
    }

}
