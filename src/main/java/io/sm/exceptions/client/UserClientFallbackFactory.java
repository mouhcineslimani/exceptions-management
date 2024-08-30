package io.sm.exceptions.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable cause) {
        return new UserClientFallback() {

            @Override
            @CircuitBreaker(name = "userClient", fallbackMethod = "getAllUsersFallback")
            @TimeLimiter(name = "userClient")
            public List<UserDTO> getAllUsers() {
                logError("getAllUsers", cause);
                return super.getAllUsers();
            }

            @Override
            @CircuitBreaker(name = "userClient", fallbackMethod = "getUserByIdFallback")
            @TimeLimiter(name = "userClient")
            public UserDTO getUserById(Long id) {
                logError("getUserById", cause);
                return super.getUserById(id);
            }

            @Override
            @CircuitBreaker(name = "userClient", fallbackMethod = "createUserFallback")
            @TimeLimiter(name = "userClient")
            public UserDTO createUser(UserDTO user) {
                logError("createUser", cause);
                return super.createUser(user);
            }

            @Override
            @CircuitBreaker(name = "userClient", fallbackMethod = "updateUserFallback")
            @TimeLimiter(name = "userClient")
            public UserDTO updateUser(Long id, UserDTO user) {
                logError("updateUser", cause);
                return super.updateUser(id, user);
            }

            @Override
            @CircuitBreaker(name = "userClient", fallbackMethod = "deleteUserFallback")
            @TimeLimiter(name = "userClient")
            public void deleteUser(Long id) {
                logError("deleteUser", cause);
                super.deleteUser(id);
            }

            private void logError(String methodName, Throwable cause) {
                System.err.println("Fallback error in method: " + methodName + ", cause: " + cause.getMessage());
            }

            // Fallback methods with resilience annotations
            public List<UserDTO> getAllUsersFallback(Throwable throwable) {
                logError("getAllUsersFallback", throwable);
                return super.getAllUsers();
            }

            public UserDTO getUserByIdFallback(Long id, Throwable throwable) {
                logError("getUserByIdFallback", throwable);
                return super.getUserById(id);
            }

            public UserDTO createUserFallback(UserDTO user, Throwable throwable) {
                logError("createUserFallback", throwable);
                return super.createUser(user);
            }

            public UserDTO updateUserFallback(Long id, UserDTO user, Throwable throwable) {
                logError("updateUserFallback", throwable);
                return super.updateUser(id, user);
            }

            public void deleteUserFallback(Long id, Throwable throwable) {
                logError("deleteUserFallback", throwable);
                super.deleteUser(id);
            }
        };
    }
}
