package io.sm.exceptions.client;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<UserDTO> fetchAllUsers() {
        return userClient.getAllUsers();
    }

    public UserDTO fetchUserById(Long id) {
        return userClient.getUserById(id);
    }

    public UserDTO addUser(UserDTO user) {
        return userClient.createUser(user);
    }

    public UserDTO modifyUser(Long id, UserDTO user) {
        return userClient.updateUser(id, user);
    }

    public void removeUser(Long id) {
        userClient.deleteUser(id);
    }
}
