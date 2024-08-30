package io.sm.exceptions.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable cause) {
        return new UserClientFallback() {
            @Override
            public List<UserDTO> getAllUsers() {
                logError("getAllUsers", cause);
                return super.getAllUsers();
            }

            @Override
            public UserDTO getUserById(Long id) {
                logError("getUserById", cause);
                return super.getUserById(id);
            }

            @Override
            public UserDTO createUser(UserDTO user) {
                logError("createUser", cause);
                return super.createUser(user);
            }

            @Override
            public UserDTO updateUser(Long id, UserDTO user) {
                logError("updateUser", cause);
                return super.updateUser(id, user);
            }

            @Override
            public void deleteUser(Long id) {
                logError("deleteUser", cause);
                super.deleteUser(id);
            }

            private void logError(String methodName, Throwable cause) {
                System.err.println("Fallback error in method: " + methodName + ", cause: " + cause.getMessage());
            }
        };
    }
}
