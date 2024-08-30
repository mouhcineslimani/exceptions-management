package io.sm.exceptions.client;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public List<UserDTO> getAllUsers() {
        return Collections.emptyList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return UserDTO.builder()
                .id(id)
                .name("Fallback User")
                .username("fallback")
                .email("fallback@example.com")
                .build();
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        return UserDTO.builder()
                .id(-1L)
                .name("Fallback Create")
                .username("fallback_create")
                .email("fallback_create@example.com")
                .build();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        return UserDTO.builder()
                .id(id)
                .name("Fallback Update")
                .username("fallback_update")
                .email("fallback_update@example.com")
                .build();
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("Fallback: Unable to delete user with ID " + id);
    }
}
